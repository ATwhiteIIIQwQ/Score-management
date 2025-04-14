<template>
  <div class="container-fluid">
    <div class="card shadow-sm mb-3">
      <div class="card-body">
        <form @submit.prevent="handleSearch">
          <div class="d-flex flex-wrap align-items-center mb-3 gap-2">
            <div class="d-flex align-items-center flex-grow-1" style="min-width: 200px; max-width: 330px">
              <label for="userName" class="col-form-label text-center me-2" style="width: 100px">用户名</label>
              <input id="userName" v-model="user.userName" class="form-control">
            </div>
            <div class="d-flex align-items-center flex-grow-1" style="min-width: 200px; max-width: 330px">
              <label for="userRole" class="col-form-label text-center me-2" style="width: 100px">角色</label>
              <input id="userRole" v-model="user.userRole" class="form-control">
            </div>
          </div>
          <button type="submit" class="btn btn-primary m-1">搜索</button>
          <button type="button" class="btn btn-success m-1" data-bs-toggle="modal" data-bs-target="#Modal" 
                  @click="resetFrom" :class="{ disabled: currentUserRole === '教师' }">添加</button>
          <button type="button" class="btn btn-secondary m-1" @click="resetUser">重置</button>
        </form>
      </div>
    </div>
    
    <div class="card shadow-sm">
      <h2 class="m-3">用户列表</h2>
      <div class="card-body">
        <table class="table table-hover table-striped">
          <thead>
            <tr>
              <th scope="col">用户名</th>
              <th scope="col">角色</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.userId">
              <td>{{ user.userName }}</td>
              <td>{{ user.userRole }}</td>
              <td>
                <button 
                  class="btn btn-outline-primary btn-sm" 
                  data-bs-toggle="modal" 
                  data-bs-target="#Modal"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; margin-right: 10px;"
                  :class="{ disabled: currentUserRole === '教师' || user.userRole === 'admin' }"
                  @click="loadUser(user)"
                >
                  编辑
                </button>
                <button 
                  class="btn btn-outline-danger btn-sm"
                  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                  @click="deleteUser(user.userId)"
                  :class="{ disabled: currentUserRole === '教师' || user.userRole === 'admin' }"
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
  </div>

  <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="ModalLabel">
            {{ isEdit ? '修改用户' : '添加用户' }}
          </h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="handleSubmit">
            <div class="row mb-3">
              <label for="userName" class="col-form-label col-3 text-center">用户名</label>
              <div class="col-9">
                <input id="userName" v-model="form.userName" class="form-control">
              </div>
            </div>
            <div class="row mb-3">
              <label for="password" class="col-form-label col-3 text-center">密码</label>
              <div class="col-9">
                <input id="password" type="password" v-model="form.userPassword" class="form-control">
              </div>
            </div>
            <div class="row mb-3">
              <label for="userRole" class="col-form-label col-3 text-center">角色</label>
              <div class="col-9">
                <select id="userRole" v-model="form.userRole" class="form-select me-3" style="width: 250px;">
                  <option v-for="role in roles" :value="role.roleName" :key="role.roleName">
                    {{ role.roleName }}
                  </option>
                </select>
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
import userApi from '@/api/user';
import { Toast } from 'bootstrap';

export default {
  data() {
    return {
      users: [],
      userId: null,
      user: {
        userName: null,
        userRole: null,
      },
      form: {
        userName: null,
        userPassword: 123456,
        userRole: null,
      },
      roles: [
        { roleName: '学生' },
        { roleName: '教师' }, 
      ],
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
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    if (!userInfo || userInfo.userRole === '学生') {
      this.$router.replace('/students');
    }
  },
  mounted() {
    this.loadUsers(this.currentPage, this.pageSize);
    this.toast = new Toast(this.$refs.toastElement, {
      autohide: true,
      delay: 2000
    });
  },
  methods: {
    async loadUsers(currentPage, pageSize) {
        try {
            const response = await userApi.getUserByPage(currentPage, pageSize);
            this.users = response.content;
            this.totalPages = response.totalPages;
            this.totalElements = response.totalElements;
        } catch (error) {
            console.error('加载用户列表失败:', error);
            this.toastMessage = error.message || '加载用户列表失败！';
            this.toast.show();
        }
    },
    async deleteUser(userId) {
        if (confirm('确定删除用户吗？')) {
            try {
                await userApi.deleteUser(userId);
                if (this.users.length === 1 && this.currentPage > 1) {
                    this.currentPage -= 1;
                }
                await this.loadUsers(this.currentPage, this.pageSize);
                this.toastMessage = '删除成功！';
                this.toast.show();
            } catch (error) {
                console.error('删除失败:', error);
                this.toastMessage = error.message || '删除失败！';
                this.toast.show();
            }
        }
    },
    async handleSubmit() {
      if (!this.form.userName || !this.form.userPassword) {
        this.toastMessage = '用户名与密码不能为空！';
        this.toast.show();
        return;
      }
      if (this.courseId) {
        try {
          await userApi.updateUser(this.courseId, this.form);
          this.loadUsers(this.currentPage, this.pageSize);
          this.toastMessage = '更新成功！';
          this.toast.show();
          this.courseId = null;
          this.form = { userName: null, password: 123456, userRole: null };
        } catch (error) {
          console.error('更新失败:', error);
          this.toastMessage = error.message || '更新失败！';
          this.toast.show();
        }
      } else {
        try { 
          await userApi.addUser(this.form);
          this.loadUsers(this.currentPage, this.pageSize);
          this.toastMessage = '添加成功！';
          this.toast.show();
          this.form = { userName: null, password: 123456, userRole: null };
        } catch (error) {
          console.error('添加失败:', error);
          this.toastMessage = error.message || '添加失败！';
          this.toast.show();
        }
      }
    },
    async handleSearch() {
        try {
            const response = await userApi.searchUsers(this.user);
            this.users = response;
        } catch (error) {
            console.error('搜索失败:', error);
            this.toastMessage = error.message || '搜索失败！';
            this.toast.show();
        }
    },
    resetUser() {
      this.user = { userName: null, userRole: null };
      this.loadUsers();  
    },
    resetFrom() {
      this.form = { userName: null, userPassword: 123456, userRole: null };
      this.isEdit = false; 
    },
    async loadUser(user) {
      try {
        this.isEdit = true;
        this.form.userName = user.userName;
        this.form.userRole = user.userRole;
      } catch (error) {
        console.error('加载课程信息失败:', error);
        this.toastMessage = error.message || '加载课程信息失败！';
        this.toast.show();
      }
    },
    async handlePageChange(page) {
      this.currentPage = page;
      this.loadUsers(page, this.pageSize);
    }
  }
};
</script>
