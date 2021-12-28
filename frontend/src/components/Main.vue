<template>
  <html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
  </head>
  <body>

  <div class="container">

    <div class="row">
      <div class="col-12">

        <div class="button">
          <router-link class="button" to="/information">О нас</router-link>
          <router-view></router-view>
        </div>
      </div>
    </div>

    <div class="row" v-show="$route.path ==='/'">

      <div class="col block">
        <div class="search">
          <div class="players">
            <div class="player">
              <input type="text" class="custom-input b-skeleton-input user">
            </div>
          </div>
          <div class="create-button">
            <input type="button" @click="createEmptyGame" class="double-border-button" value="Создать игру">
            <input type="button" @click="createGame" class="double-border-button" value="Присоединиться">
          </div>
        </div>
      </div>
    </div>

    <div class="footer row">
      <div class="col-8 block footer-1">
      </div>
      <div class="col-4 block footer-2">
      </div>
    </div>
  </div>


  </body>
  </html>
</template>

<script>

import {connect, createGame, joinGame} from "@/util/WSUtil";

export default {
  name: 'Main',
  mounted() {
    connect()
  },
  props: {
    msg: String
  },
  methods: {
    createEmptyGame: function () {
      const user = this.$el.querySelector('.user');
      createGame(user.value)
    },
    createGame: function () {
      const uuid = this.$el.querySelector('.user');
      joinGame(uuid)
    }

  }
}
</script>

<style scoped>

.double-border-button {
  text-decoration: none;
  display: inline-block;
  margin: 10px 20px;
  padding: 10px 30px;
  position: relative;
  border: 2px solid #00416a;
  width: 180px;
  color: #00416a;
  font-family: 'Montserrat', sans-serif;
  transition: .4s;
}

.double-border-button:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  width: 100%;
  height: 100%;
  margin: auto;
  border: 2px solid rgba(0, 0, 0, 0);
  transition: .4s;
}

.double-border-button:hover:after {
  border-color: #f1c40f;
  width: calc(100% - 10px);
  height: calc(100% + 10px);
}

.custom-input {
  width: 240px;
}


.create-button {
  width: 100%;
}

.search {
  height: 800px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-wrap: wrap;
}

.player {
  width: 100%;
  padding: 15px;
}

.footer-1 {
  height: 100px;
}

.footer-2 {
  height: 100px;
}

.button {
  width: 150px;
  text-decoration: none;
  margin: 15px;
  background-color: #00416a;
  border-radius: 5px;
  text-align: center;
  border: 2px solid #00416a;
}
</style>
