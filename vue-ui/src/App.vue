<template>
  <div id="app app-container">
    <NavBar v-if="$route.path !== '/login'" />
    <main :class="{ 'main-container': $route.path !== '/login' }">
      <router-view />
    </main>  
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue';

export default {
  components: {
    NavBar
  },
};
</script>

<style scoped>
.app-container {
  display: flex;
  min-height: 100vh;
}
.main-container {
  flex: 1;
  margin-left: 250px;
  padding: 25px;
  overflow-y: auto;
}
</style>

