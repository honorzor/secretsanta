<template>
  <div id="app">
    <Main/>
  </div>
</template>

<script>
import Main from './components/Main.vue'

export default {
  name: 'App',
  components: {
    Main
  }
}
</script>

<style>

body {
  margin: 0;
  font-size: 15px;
  background: #00416a; /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #00416a, #e4e5e6); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #00416a, #e4e5e6);
}

</style>
