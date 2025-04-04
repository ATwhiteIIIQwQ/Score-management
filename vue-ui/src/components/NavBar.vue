<template>
    <div class="sidebar bg-dark text-white vh-100 p-3">
        <div class="d-flex align-items-center">
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
        </nav>
    </div>
</template>

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

</style>
