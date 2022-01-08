<template>
  <div id="app">
    <Information></Information>
    <router-view></router-view>
  </div>
</template>

<script>

import Information from "@/components/Information";
export default {
  name: 'App',
  components: {Information}
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
