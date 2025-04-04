<template>
    <div class="container">
        <form @submit.prevent="handleSearch">
            <div class="row mb-3">
                <label for="studentNum" class="col-form-label col-1 text-center">学号</label>
                <div class="col-2">
                    <input id="studentNum" v-model="student.studentNum" class="form-control" placeholder="学号">
                </div>
                <label for="studentName" class="col-form-label col-1 text-center">姓名</label>
                <div class="col-2">
                    <input id="studentName" v-model="student.studentName" class="form-control" placeholder="姓名">
                </div>
                <label for="studentGrade" class="col-form-label col-1 text-center">年级</label>
                <div class="col-2">
                    <input id="studentGrade" v-model="student.studentGrade" class="form-control" type="number" placeholder="年级">
                </div>
                <label for="studentClass" class="col-form-label col-1 text-center">班级</label>
                <div class="col-2">
                    <input id="studentClass" v-model="student.studentClass" class="form-control" placeholder="班级">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">搜索</button>
            <button type="button" class="btn btn-secondary m-2" @click="handleReset">重置</button>
        </form>

      <h2>学生列表</h2>
      <table class="table table-hover table-striped">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">学号</th>
            <th scope="col">姓名</th>
            <th scope="col">年级</th>
            <th scope="col">班级</th>
            <th scope="col">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="student in students" :key="student.studentId">
              <td>{{ student.studentId }}</td>
              <td>{{ student.studentNum }}</td>
              <td>{{ student.studentName }}</td>
              <td>{{ student.studentGrade }}</td>
              <td>{{ student.studentClass }}</td>
              <td>
                <button class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#Modal" @click="loadStudent(student)">修改</button>
                <button class="btn btn-danger btn-sm" @click="deleteStudent(student.studentId)">删除</button>
              </td>
            </tr>
        </tbody>
      </table>

      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#Modal">
        添加学生
      </button>

      <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="ModalLabel">添加学生</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form @submit.prevent="handleSubmit">
                <div class="row mb-3">
                  <label for="studentNum" class="col-form-label col-2 text-center">学号</label>
                  <div class="col-10">
                    <input id="studentNum" v-model="form.studentNum" class="form-control" placeholder="学号">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="studentName" class="col-form-label col-2 text-center">姓名</label>
                  <div class="col-10">
                    <input id="studentName" v-model="form.studentName" class="form-control" placeholder="姓名">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="studentGrade" class="col-form-label col-2 text-center">年级</label>
                  <div class="col-10">
                    <input id="studentGrade" v-model="form.studentGrade" class="form-control" type="number" placeholder="年级">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="studentClass" class="col-form-label col-2 text-center">班级</label>
                  <div class="col-10">
                    <input id="studentClass" v-model="form.studentClass" class="form-control" placeholder="班级">
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
                提交
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>
  
<script>
  import studentApi from '@/api/student';
  
  export default {
    data() {
      return {
        students: [],
        studentId: null,
        student: {
          studentNum: null,
          studentName: null,
          studentGrade: null,
          studentClass: null,
        },
        form: {
          studentNum: null,
          studentName: null,
          studentGrade: null,
          studentClass: null,
        }
      };
    },
    mounted() {
      this.loadStudents();
    },
    methods: {
      async loadStudents() {
        try {
          const response = await studentApi.getAllStudents();
          this.students = response.data;
        } catch (error) {
          console.error('加载学生列表失败:', error);
        }
      },
      async deleteStudent(studentId) {
        if (confirm('确定删除学生吗？')) {
          try {
            await studentApi.deleteStudent(studentId);
            this.loadStudents();
          } catch (error) {
            console.error('删除失败:', error);
          }
        }
      },
      async handleSubmit() {
        if (this.studentId) {
          try {
            await studentApi.updateStudent(this.studentId, this.form);
            this.loadStudents();
            this.studentId = null;
            this.form = { studentNum: null, studentName: null, studentGrade: null, studentClass: null };
          } catch (error) {
            console.error('更新失败:', error);
          }
        } else {
          try { 
            await studentApi.addStudent(this.form);
            this.loadStudents();
            this.form = { studentNum: null, studentName: null, studentGrade: null, studentClass: null };
          } catch (error) {
            console.error('添加失败:', error);
          }
        }
      },
      async handleSearch() {
        try {
          const response = await studentApi.searchStudents(this.student);
          this.students = response.data; 
        } catch (error) {
          console.error('搜索失败:', error);
        }
      },
      handleReset() {
        this.student = { studentNum: null, studentName: null, studentGrade: null, studentClass: null };
        this.loadStudents();  
      },
      async loadStudent(student) {
        try {
          this.form.studentNum = student.studentNum;
          this.form.studentName = student.studentName;
          this.form.studentGrade = student.studentGrade;
          this.form.studentClass = student.studentClass;
          this.studentId = student.studentId;
        } catch (error) {
          console.error('加载学生信息失败:', error);
        }
      },
    }
  };
</script>
  
<style scoped>

</style>
