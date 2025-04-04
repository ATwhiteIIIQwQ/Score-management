<template>
    <div class="navbar-container">
        <nav class="navbar">
            <router-link class="nav-link" to="/students">
                学生管理
            </router-link>
            <router-link class="nav-link" to="/courses">
                课程管理
            </router-link>
            <router-link class="nav-link" to="/scores">
                成绩管理
            </router-link>
        </nav>
    </div>
</template>

<style scoped>
  .navbar-container {
    position: fixed;
    left: 0;
    top: 0;
    bottom: 0;
    width: 200px;
  }
  .navbar {
    width: 200px;
    background: #f0f0f0;
    padding: 20px;
    border-right: 1px solid #ddd;
  }
  .nav-link {
  display: block;
  padding: 10px;
  margin: 5px 0;
  color: #333;
  text-decoration: none;
  border-radius: 4px;
  }
  .nav-link:hover {
    background: #e0e0e0;
  }
</style>

<script>

</script>