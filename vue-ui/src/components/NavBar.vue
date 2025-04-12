<template>
    <div class="sidebar bg-dark text-white vh-100 p-3 d-flex flex-column">
        <div class="d-flex align-items-center border-down">
            <img src="../assets/icons/logo.svg" alt="Logo" class="m-1" height="35px">
            <strong class="text-center p-2" style="font-size:x-large;">成绩管理系统</strong>
        </div>
        <hr class="bg-white">
        <nav class="nav flex-column">
            <router-link 
                to="/students" 
                class="nav-link text-white" 
                active-class="active">
                学生管理
            </router-link>
            <router-link 
                to="/courses" 
                class="nav-link text-white"
                active-class="active">
                课程管理
            </router-link>
            <router-link 
                to="/scores" 
                class="nav-link text-white"
                active-class="active">
                成绩管理
            </router-link>
            <router-link 
                to="/users" 
                class="nav-link text-white"
                :style="{ display: userInfo.userRole === '学生' ? 'none' : 'block' }"
                active-class="active">
                用户管理
            </router-link>
        </nav>
        
        <div class="mt-auto">
            <hr class="bg-white">
            <div v-if="userInfo" class="d-flex align-items-center justify-content-between">
                <strong class="text-white" style="margin-left: 10px;">{{ userInfo.userName }}</strong>
                <button 
                    @click="handleLogout"
                    class="btn btn-sm btn-outline-danger"
                    title="退出登录"
                >退出登录
                    <i class="bi bi-box-arrow-right"></i>
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userInfo: JSON.parse(localStorage.getItem('userInfo'))
        }
    },
    methods: {
        handleLogout() {
            localStorage.removeItem('userInfo')
            this.$router.replace('/login')
        }
    }
}
</script>

<style scoped>
.sidebar {
    width: 250px;
    position: fixed;
}

.nav-link {
    transition: all 0.3s ease;
    border-radius: 4px;
    margin: 4px 0;
}

.nav-link:hover {
    background-color: #34495e !important;
    transform: translateX(5px);
}

.active {
    background-color: #3498db !important;
    font-weight: bold;
}

.btn-outline-danger {
    --bs-btn-color: #dc3545;
    --bs-btn-border-color: #dc3545;
    --bs-btn-hover-bg: #dc3545;
}
</style>
