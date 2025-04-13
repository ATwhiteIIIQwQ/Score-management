<template>
  <div class="container-fluid">
    <div class="card shadow-sm mb-3">
      <div class="card-body">
        <form @submit.prevent="handleSearch">
          <div class="d-flex flex-wrap align-items-center mb-3 gap-2">
            <div class="d-flex align-items-center flex-grow-1" style="min-width: 200px; max-width: 330px">
              <label for="studentId" class="col-form-label text-center me-2" style="width: 100px">学生</label>
              <select id="student" v-model="score.studentId" class="form-select">
                <option v-for="student in students" :value="student.studentId" :key="student.studentId">
                  {{ student.studentName }}
                </option>
              </select>
            </div>
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
              <th scope="col">学生</th>
              <th scope="col">课程</th>
              <th scope="col">成绩</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="score in scores" :key="score.scoreId">
              <td>{{ score.studentName }}</td>
              <td>{{ score.courseName }}</td>
              <td>{{ score.score }}</td>
              <td>
                <button
                  class="btn btn-outline-primary btn-sm" 
                  data-bs-toggle="modal" 
                  data-bs-target="#Modal"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; margin-right: 10px;"
                  :class="{ disabled: currentUserRole === '学生' }"
                  @click="loadScore(score)">
                  编辑
                </button>
                <button
                  class="btn btn-outline-danger btn-sm"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                  :class="{ disabled: currentUserRole === '学生' }"
                  @click="deleteScore(score.scoreId)">
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

  <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="ModalLabel">
            {{ isEdit ? '修改成绩' : '添加成绩' }}
          </h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="handleSubmit">
            <div class="row mb-3">
              <label for="student" class="col-form-label col-2 text-center">学生</label>
              <div class="col-10">
                <select id="student" v-model="form.studentId" class="form-select">
                  <option v-for="student in students" :value="student.studentId" :key="student.studentId">
                    {{ student.studentName }}
                  </option>
                </select>
              </div>
            </div>
            <div class="row mb-3">
              <label for="course" class="col-form-label col-2 text-center">课程</label>
              <div class="col-10">
                <select id="course" v-model="form.courseId" class="form-select">
                  <option v-for="course in courses" :value="course.courseId" :key="course.courseId">
                    {{ course.courseName }}
                  </option>
                </select>
              </div>
            </div>
            <div class="row mb-3">
              <label for="score" class="col-form-label col-2 text-center">成绩</label>
              <div class="col-10">
                <input id="score" v-model="form.score" type="number" class="form-control">
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
          <button 
            type="submit" 
            class="btn btn-primary"
            @click="handleSubmit"
            data-bs-dismiss="modal">
            {{ isEdit ? '更新' : '提交' }}
          </button>
        </div>
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
      scores: [],
      students: [],
      courses: [],
      scoreId: null,
      score: {
        studentId: null,
        courseId: null,
        score: null
      },
      form: {
        studentId: null,
        courseId: null,
        score: null
      },
      toastMessage: '',
      toast: null,
      isEdit: false,
      isSearch: false,
      totalPages: 0,
      totalElements: 0,
      currentPage: 1,
      pageSize: 10,
      currentUserRole: JSON.parse(localStorage.getItem('userInfo'))?.userRole || '',
    };
  },
  beforeMount() {
    if (!localStorage.getItem('userInfo')) {
      this.$router.replace('/LoginIndex')
    }
  },
  mounted() {
    this.loadScores(this.currentPage, this.pageSize);
    this.loadStudents();
    this.loadCourses();
    this.toast = new Toast(this.$refs.toastElement, {
      autohide: true,
      delay: 2000
    });
  },
  methods: {
    async loadScores(currentPage, pageSize) {
      try {
        const response = await scoreApi.getScoreByPage(currentPage, pageSize);
        this.scores = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      } catch (error) {
        console.error('加载分数列表失败:', error);
        this.toastMessage = error.message || '加载分数列表失败！';
        this.toast.show();
      }
    },
    async loadStudents() {
      try {
        const response = await studentApi.getAllStudents();
        this.students = response;
      } catch (error) {
        console.error('加载学生列表失败:', error);
        this.toastMessage = error.message || '加载学生列表失败！';
        this.toast.show();
      }
    },
    async loadCourses() {
      try {
        const response = await courseApi.getAllCourses();
        this.courses = response;
      } catch (error) {
        console.error('加载课程列表失败:', error);
        this.toastMessage = error.message || '加载课程列表失败！';
        this.toast.show();
      }
    },
    async deleteScore(scoreId) {
      if (confirm('确定删除分数吗？')) {
        try {
          await scoreApi.deleteScore(scoreId);
          this.toastMessage = '删除成功！';
          this.toast.show();
          if (this.scores.length === 1 && this.currentPage > 1) {
            this.currentPage -= 1;
          }
          this.loadScores(this.currentPage, this.pageSize);
        } catch (error) {
          console.error('删除失败:', error);
          this.toastMessage = error.message || '删除失败！';
          this.toast.show();
        }
      }
    },
    async handleSubmit() {
      if (this.scoreId) {
        try {
          await scoreApi.updateScore(this.scoreId, this.form);
          this.loadScores(this.currentPage, this.pageSize);
          this.toastMessage = '更新成功！';
          this.toast.show();
          this.scoreId = null;
          this.form = { studentId: null, courseId: null, score: null };
        } catch (error) {
          console.error('更新失败:', error);
          this.toastMessage = error.message || '更新失败，请检查数据格式！';
          this.toast.show();
        }
      } else {
        try {
          await scoreApi.addScore(this.form);
          this.loadScores(this.currentPage, this.pageSize);
          this.toastMessage = '添加成功！';
          this.toast.show();
          this.form = { studentId: null, courseId: null, score: null };
        } catch (error) {
          console.error('添加失败:', error);
          this.toastMessage = error.message || '添加失败，请检查数据格式！';
          this.toast.show();
        }
      }
    },
    async handleSearch() {
      try {
        const params = {
          ...this.score,
          pageNum: this.currentPage,
          pageSize: this.pageSize
        };
        const response = await scoreApi.searchScores(params);
        this.scores = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
        this.isSearch = true;
      } catch (error) {
        console.error('搜索失败:', error);
        this.toastMessage = error.message || '搜索失败，请检查数据格式！';
        this.toast.show();
      }
    },
    resetScore() {
      this.score = { studentId: null, courseId: null, score: null };
      this.loadScores();
    },
    resetFrom() {
      this.form = { studentId: null, courseId: null, score: null };
      this.isEdit = false;
    },
    async loadScore(score) {
      try {
        this.isEdit = true;
        this.form.studentId = score.studentId;
        this.form.courseId = score.courseId;
        this.form.score = score.score;
        this.scoreId = score.scoreId;
      } catch (error) {
        console.error('加载课程信息失败:', error);
        this.toastMessage = error.message || '加载课程信息失败！';
        this.toast.show();
      }
    },
    async handlePageChange(page) {
      this.currentPage = page;
      if (this.isSearch) {
        await this.handleSearch();
      } else {
        this.loadScores(page, this.pageSize);
      }
    }
  }
};

</script>

<style scoped>

</style>