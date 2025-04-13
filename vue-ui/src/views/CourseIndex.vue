<template>
  <div class="container-fluid">
    <div class="card shadow-sm mb-3">
      <div class="card-body">
        <form @submit.prevent="handleSearch">
          <div class="d-flex flex-wrap align-items-center mb-3 gap-2">
            <div class="d-flex align-items-center flex-grow-1" style="min-width: 200px; max-width: 330px">
              <label for="courseName" class="col-form-label text-center me-2" style="width: 100px">课程名称</label>
              <input id="courseName" v-model="course.courseName" class="form-control">
            </div>
            <div class="d-flex align-items-center flex-grow-1" style="min-width: 200px; max-width: 330px">
              <label for="courseCredit" class="col-form-label text-center me-2" style="width: 100px">学分</label>
              <input id="courseCredit" v-model="course.courseCredit" class="form-control" type="number">
            </div>
          </div>
          <button type="submit" class="btn btn-primary m-1">搜索</button>
          <button type="button" class="btn btn-success m-1" data-bs-toggle="modal" data-bs-target="#Modal" 
                  @click="resetFrom" :class="{ disabled: currentUserRole === '学生' }">添加</button>
          <button type="button" class="btn btn-secondary m-1" @click="resetCourse">重置</button>
        </form>
      </div>
    </div>
    
    <div class="card shadow-sm">
      <h2 class="m-3">课程列表</h2>
      <div class="card-body">
        <table class="table table-hover table-striped">
          <thead>
            <tr>
              <th scope="col">课程名称</th>
              <th scope="col">学分</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="course in courses" :key="course.courseId">
              <td>{{ course.courseName }}</td>
              <td>{{ course.courseCredit }}</td>
              <td>
                <button 
                  class="btn btn-outline-primary btn-sm" 
                  data-bs-toggle="modal" 
                  data-bs-target="#Modal"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; margin-right: 10px;"
                  :class="{ disabled: currentUserRole === '学生' }"
                  @click="loadCourse(course)"
                >
                  编辑
                </button>
                <button 
                  class="btn btn-outline-danger btn-sm"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                  :class="{ disabled: currentUserRole === '学生' }"
                  @click="deleteCourse(course.courseId)"
                >
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

    <!-- 模态框 -->
    <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="ModalLabel">
              {{ isEdit ? '修改课程' : '添加课程' }}
            </h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="handleSubmit">
              <div class="row mb-3">
                <label for="courseName" class="col-form-label col-3 text-center">课程名称</label>
                <div class="col-9">
                  <input id="courseName" v-model="form.courseName" class="form-control">
                </div>
              </div>
              <div class="row mb-3">
                <label for="courseCredit" class="col-form-label col-3 text-center">学分</label>
                <div class="col-9">
                  <input id="courseCredit" v-model="form.courseCredit" class="form-control" type="number">
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

    <!-- Toast提示 -->
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
  </div>
</template>

<script>
import courseApi from '@/api/course';
import { Toast } from 'bootstrap';

export default {
  data() {
    return {
      courses: [],
      courseId: null,
      course: {
        courseName: null,
        courseCredit: null,
      },
      form: {
        courseName: null,
        courseCredit: null,
      },
      toastMessage: '',
      toast: null,
      isEdit: false,
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
    this.loadCourses(this.currentPage, this.pageSize);
    this.toast = new Toast(this.$refs.toastElement, {
      autohide: true,
      delay: 2000
    });
  },
  methods: {
    async loadCourses(currentPage, pageSize) {
      try {
        const response = await courseApi.getCourseByPage(currentPage, pageSize);
        this.courses = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      } catch (error) {
        console.error('加载课程列表失败:', error);
        this.toastMessage = error.message || '加载课程列表失败！';
        this.toast.show();
      }
    },
    async deleteCourse(courseId) {
      if (confirm('确定删除课程吗？')) {
        try {
          await courseApi.deleteCourse(courseId);
          this.toastMessage = error.message || '删除成功！';
          this.toast.show();
          if (this.students.length === 1 && this.currentPage > 1) {
              this.currentPage -= 1;
            }
          this.loadCourses();
        } catch (error) {
          console.error('删除失败:', error);
          this.toastMessage = error.message || '删除失败！';
          this.toast.show();
        }
      }
    },
    async handleSubmit() {
      if (this.courseId) {
        try {
          await courseApi.updateCourse(this.courseId, this.form);
          this.loadCourses();
          this.toastMessage = '更新成功！';
          this.toast.show();
          this.courseId = null;
          this.form = { courseName: null, courseCredit: null };
        } catch (error) {
          console.error('更新失败:', error);
          this.toastMessage = error.message || '更新失败！';
          this.toast.show();
        }
      } else {
        try { 
          await courseApi.addCourse(this.form);
          this.loadCourses();
          this.toastMessage = '添加成功！';
          this.toast.show();
          this.form = { courseName: null, courseCredit: null };
        } catch (error) {
          console.error('添加失败:', error);
          this.toastMessage = error.message || '添加失败！';
          this.toast.show();
        }
      }
    },
    async handleSearch() {
      try {
        const response = await courseApi.searchCourses(this.course);
        this.courses = response; 
      } catch (error) {
        console.error('搜索失败:', error);
        this.toastMessage = error.message || '搜索失败！';
        this.toast.show();
      }
    },
    resetCourse() {
      this.course = { courseName: null, courseCredit: null };
      this.loadCourses();  
    },
    resetFrom() {
      this.form = { courseName: null, courseCredit: null };
      this.isEdit = false; 
    },
    async loadCourse(course) {
      try {
        this.isEdit = true;
        this.form.courseName = course.courseName;
        this.form.courseCredit = course.courseCredit;
        this.courseId = course.courseId;
      } catch (error) {
        console.error('加载课程信息失败:', error);
        this.toastMessage = error.message || '加载课程信息失败！';
        this.toast.show();
      }
    },
    async handlePageChange(page) {
      this.currentPage = page;
      this.loadCourses(page, this.pageSize);
    }
  }
};
</script>

<style scoped>

</style>