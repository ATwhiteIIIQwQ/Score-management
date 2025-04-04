<template>
    <div class="container">
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
                <button 
                  class="btn btn-success" 
                  data-bs-toggle="modal" 
                  data-bs-target="#exampleModal" 
                  @click="loadStudentForEdit(student.studentId)">  <!-- 修改点击事件 -->
                  修改
                </button>
                <button class="btn btn-danger" @click="deleteStudent(student.studentId)">删除</button>
              </td>
            </tr>
        </tbody>
      </table>

      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        添加学生
      </button>

      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel">添加学生</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form @submit.prevent="handleSubmit">
                <div class="row mb-3">
                  <label for="studentNum" class="col-form-label col-2">学号</label>
                  <div class="col-10">
                    <input id="studentNum" v-model="student.studentNum" class="form-control" placeholder="学号">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="studentName" class="col-form-label col-2">姓名</label>
                  <div class="col-10">
                    <input id="studentName" v-model="student.studentName" class="form-control" placeholder="姓名">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="studentGrade" class="col-form-label col-2">年级</label>
                  <div class="col-10">
                    <input id="studentGrade" v-model="student.studentGrade" class="form-control" type="number" placeholder="年级">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="studentClass" class="col-form-label col-2">班级</label>
                  <div class="col-10">
                    <input id="studentClass" v-model="student.studentClass" class="form-control" placeholder="班级">
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
          studentNum: '',
          studentName: '',
          studentGrade: null,
          studentClass: '',
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
            this.loadStudents(); // 刷新列表
          } catch (error) {
            console.error('删除失败:', error);
          }
        }
      },
      async handleSubmit() {
        if (this.studentId) {
          await this.handleUpdate(this.studentId, this.student);
        } else {
          try { 
            await studentApi.addStudent(this.student);
            this.loadStudents();
            this.student = { studentNum: '', studentName: '', studentGrade: '', studentClass: '' };
          } catch (error) {
            console.error('添加失败:', error);
          }
        }
      },
      // 在methods中添加新的方法
      async loadStudentForEdit(studentId) {
        try {
          // const response = await studentApi.getStudentById(studentId);
          // this.student = response.data;
          this.studentId = studentId;
        } catch (error) {
          console.error('加载学生信息失败:', error);
        }
      },
      async handleUpdate(studentId, student) {
        try {
          // 修改参数为完整的对象
          await studentApi.updateStudent(studentId, {
            studentNum: student.studentNum,
            studentName: student.studentName,
            studentGrade: student.studentGrade,
            studentClass: student.studentClass
          });
          this.loadStudents();
          this.studentId = null;
          this.student = { studentNum: '', studentName: '', studentGrade: '', studentClass: '' };
        } catch (error) {
          console.error('更新失败:', error);
        }
      }
    }
  };
</script>
  
<style scoped>

</style>