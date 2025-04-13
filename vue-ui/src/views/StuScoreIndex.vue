<template>
  <div class="container-fluid">
    <div class="card shadow-sm mb-3">
      <div class="card-body">
        <form @submit.prevent="handleSearch">
          <div class="d-flex flex-wrap align-items-center mb-3 gap-2">
            <div class="d-flex align-items-center flex-grow-1" style="min-width: 200px; max-width: 330px">
              <label for="courseId" class="col-form-label text-center me-2" style="width: 100px">课程</label>
              <select id="course" v-model="score.courseId" class="form-select">
                <option v-for="course in courses" :value="course.courseId" :key="course.courseId">
                  {{ course.courseName }}
                </option>
              </select>
            </div>
            <div class="d-flex align-items-center flex-grow-1" style="min-width: 200px; max-width: 330px">
              <label for="score" class="col-form-label text-center me-2" style="width: 100px">成绩</label>
              <input id="score" v-model="score.score" class="form-control" type="number">
            </div>
          </div>
          <button type="submit" class="btn btn-primary m-1">搜索</button>
          <button type="button" class="btn btn-success m-1" data-bs-toggle="modal" data-bs-target="#Modal" @click="resetFrom" :class="{ disabled: currentUserRole === '学生' }">添加</button>
          <button type="button" class="btn btn-secondary m-1" @click="resetScore">重置</button>
        </form>
      </div>
    </div>

    <div class="card shadow-sm">
      <h2 class="m-3">成绩列表</h2>
      <div class="card-body">
        <table class="table table-hover table-striped">
          <thead>
            <tr>
              <th scope="col">课程</th>
              <th scope="col">成绩</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="score in scores" :key="score.scoreId">
              <td>{{ score.courseName }}</td>
              <td>{{ score.score }}</td>
              <td>
                <button
                  class="btn btn-outline-primary btn-sm disabled" 
                  data-bs-toggle="modal" 
                  data-bs-target="#Modal"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; margin-right: 10px;">
                  编辑
                </button>
                <button
                  class="btn btn-outline-danger btn-sm disabled"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">
                  删除
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <nav aria-label="Page navigation">
          <ul class="pagination justify-content-end">
            <li class="page-item" :class="{ disabled: currentPage === 1  || totalPages === 0 }">
              <a class="page-link" href="#" @click.prevent="handlePageChange(currentPage - 1)">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="page-item" v-if="currentPage > 1 && totalPages != 0">
              <a class="page-link" href="#" @click.prevent="handlePageChange(currentPage - 1)">
                {{ currentPage - 1 }}
              </a>
            </li>
            <li class="page-item active">
              <a class="page-link" href="#">
                {{ currentPage }}
              </a>
            </li>
            <li class="page-item" v-if="currentPage < totalPages && totalPages != 0">
              <a class="page-link" href="#" @click.prevent="handlePageChange(currentPage + 1)">
                {{ currentPage + 1 }}
              </a>
            </li>
            <li class="page-item" :class="{ disabled: currentPage === totalPages || totalPages === 0 }">
              <a class="page-link" href="#" @click.prevent="handlePageChange(currentPage + 1)">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
        
      </div>
    </div>
  </div>

  <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
    <div ref="toastElement" class="toast align-items-center text-white bg-dark border-0" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
        {{ toastMessage }}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    </div>
  </div>
</template>

<script>
import scoreApi from '@/api/score';
import studentApi from '@/api/student';
import courseApi from '@/api/course';
import { Toast } from 'bootstrap';

export default {
  data() {
    return {
      currentUserRole: JSON.parse(localStorage.getItem('userInfo'))?.userRole || '',
      currentUserName: JSON.parse(localStorage.getItem('userInfo'))?.userName || '',
      scores: [],
      students: [],
      courses: [],
      scoreId: null,
      studentId: null,
      score: {
        courseId: null,
        score: null
      },
      toastMessage: '',
      toast: null,
      totalPages: 0,
      totalElements: 0,
      currentPage: 1,
      pageSize: 10,
    };
  },
  beforeMount() {
    if (!localStorage.getItem('userInfo')) {
      this.$router.replace('/LoginIndex')
    }
  },
  mounted() {
    this.loadStudents();
    this.loadCourses();
    this.toast = new Toast(this.$refs.toastElement, {
      autohide: true,
      delay: 2000
    });
  },
  methods: {
    async loadStudents() {
      try {
        const response = await studentApi.getAllStudents();
        this.students = response;
        
        const currentUser = this.students.find(
          student => student.studentNum === this.currentUserName
        );
        
        if (currentUser) {
          this.studentId = currentUser.studentId;
          await this.handleSearch();
        } else {
          console.warn('未找到当前用户对应的学生信息');
          this.toastMessage = '未找到当前用户对应的学生信息';
          this.toast.show();
        }
        
      } catch (error) {
        console.error('加载学生失败:', error);
        this.toastMessage = error.message || '加载学生失败！';
        this.toast.show();
      }
    },
    async loadCourses() {
      try {
        const response = await courseApi.getAllCourses();
        this.courses = response;
      } catch (error) {
        console.error('加载课程失败:', error);
        this.toastMessage = error.message || '加载课程失败！';
        this.toast.show();
      }
    },
    async handleSearch() {
      try {
        const params = {
          ...this.score,
          studentId: this.studentId,
          pageNum: this.currentPage,
          pageSize: this.pageSize
        };
        const response = await scoreApi.searchScores(params);
        this.scores = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      } catch (error) {
        console.error('搜索失败:', error);
        this.toastMessage = error.message || '搜索失败，请检查数据格式！';
        this.toast.show();
      }
    },
    resetScore() {
      this.score = { courseId: null, score: null };
      this.handleSearch();
    },
    async handlePageChange(page) {
      this.currentPage = page;
      await this.handleSearch();
    }
  }
};

</script>

<style scoped>

</style>