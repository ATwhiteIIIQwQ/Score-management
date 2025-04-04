<template>
  <div id="app">
    <NavBar />
    <main class="main">
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
.main {
  flex: 1;
  padding: 20px;
}
</style>

