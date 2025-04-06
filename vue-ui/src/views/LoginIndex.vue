<template>

  <div class="LoginIndex">
    <div class="content">
      <div class="login_container" style="color: #eefdf1;">
        <h1 style="text-align: center; font-family: STHupo; font-size: 4em;">Login</h1>
        <div class="form_container">
          <form @submit.prevent="handleLogin">
            <div class="form-group">
              <label class="label">Username:</label>
              <input type="text" v-model="username" required placeholder="Enter username" />
            </div>
            <div class="form-group">
              <label class="label">Password:</label>
              <input type="password" v-model="password" required placeholder="Enter password" />
            </div>
            <div v-if="message" class="alert" :class="{'alert-danger': isError, 'alert-success': !isError}" role="alert" style="--bs-alert-padding-y: 10px">
              {{ message }}
            </div>
            <br><br>
            <button class="btn btn-primary" type="submit"><a class="mx-1 login">Login</a></button>
          </form>
        </div>
        <About class="fixed-bottom"/>
      </div>
    </div>
  </div>

</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import userApi from '@/api/user'

export default {
  setup() {
    const router = useRouter()
    const username = ref('')
    const password = ref('')
    const message = ref('')
    const isError = ref(false)

    const handleLogin = async () => {
      try {
        const res = await userApi.login({
          userName: username.value,
          userPassword: password.value
        })
        
        if (res.code === 200) {
          localStorage.setItem('userInfo', JSON.stringify({
            token: res.data.token,
            userName: res.data.userName,
            userRole: res.data.userRole,
            loginTime: new Date().getTime()
          }))
          message.value = '登录成功，正在跳转...'
          isError.value = false
          setTimeout(() => {
            router.push('/students')
          }, 1500)
        } else {
          message.value = res.msg || '登录失败'
          isError.value = true
        }
      } catch (error) {
        message.value = '连接服务器失败'
        isError.value = true
      }
    }

    return { username, password, message, isError, handleLogin }
  }
}
</script>

<style scoped>

.LoginIndex {
  height: 100vh;
  background: url('../assets/images/100102236_p0.jpg') no-repeat center center fixed;
  background-size: cover;
}

.content {
  backdrop-filter: blur(12px);
  background: rgba(0, 0, 0, 0.100);
}

.login_container {
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
}

label {
  margin-bottom: 5px;
}

.form-group {
  margin-bottom: 15px;
  width: 100%;
}

input {
  padding: 10px;
  width: 100%;
  border: 1px solid #00a2bf;
  border-radius: 4px;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  transition: border-color 0.3s;
}

input:focus {
  border-color: #1b4d7c;
  outline: none;
}

button {
  padding: 10px 15px;
  position: absolute;
            bottom: 0;
            right: 0;
}

.Register {
  padding: 10px 15px;
}

.form_container {
  position: relative;
}

.login {
    color: #eefdf1;
    text-decoration: none;
}

</style>