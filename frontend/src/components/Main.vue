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

      </div>
    </div>

    <div class="row">
      <div class="col block">
        <div class="search">
          <div class="players">
            <div class="player">
              <input type="text" class="custom-input b-skeleton-input user">
            </div>
          </div>
          <div class="create-button">
            <input type="button" @click="add" class="double-border-button" value="Добавить">
            <input type="button" @click="createGame" class="double-border-button" value="Создать игру">
          </div>
        </div>
      </div>

    </div>

    <div class="footer row">
      <div class="col-8 block footer-1">
<!--        footer-->
      </div>
      <div class="col-4 block footer-2">
<!--        footer-->
      </div>
    </div>
  </div>


  </body>
  </html>
</template>

<script>
import {User} from "@/dto/User";
import axios from "axios"

export default {
  name: 'Main',
  props: {
    msg: String
  },
  methods: {
    add: function () {
      const player = this.$el.querySelector(".player").cloneNode(true);
      //TODO FIX LATER
      const inputText = player.lastChild;
      inputText.value = ""
      const players = this.$el.querySelector(".players");
      players.appendChild(player)
    },
    createGame: function () {
      const users = this.$el.querySelectorAll('.user');

      const names = Array.from(users)
          .map(e => new User(e.value, e.value))

      const game = {
        players: names
      }

      axios.post("http://localhost:8081/game/create", game)
          .then(e => alert("игра создана, ответ от сервера: " + e))
          .catch(e => alert("Ошибка: " + e))
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

</style>
