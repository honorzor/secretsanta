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

  <div class="container main ct">
    <h1>Создай свою игру</h1>
    <select class="form-select w-25" aria-label="Default select example" @change="selectType($event)">
      <option selected>Выберите действие</option>
      <option value="1">Создать</option>
      <option value="2">Присоедениться</option>
    </select>
    <div class="user main w-25" v-show="wantCreate || wantJoin">
      <input type="text" class="form-control mt-1" id="email" placeholder="your email">
      <input type="text" class="form-control mt-1" id="game-id" placeholder="game id" v-show="wantJoin">
      <input type="button" value="Отправить запрос" class="btn btn-primary mt-1" @click="wantJoin ? join(): create()">
    </div>
    <div v-show="game.players != null"> {{ game }}</div>
  </div>

  </body>
  </html>
</template>

<script>

import SockJS from "sockjs-client";
import Stomp from "stompjs";

export default {
  name: 'Main',
  mounted() {
    this.connect()
  },
  data: function () {
    return {
      isStarted: false,
      wantCreate: false,
      wantJoin: false,
      game: {game: null, players: null, created: false},
      stompClient: null,
      startMessage: "Игра успешно создана"
    }
  },
  props: {
    msg: String
  },
  methods: {
    connect: function () {
      const socket = new SockJS('https://secret-santa.smn-router.keenetic.pro/ws-connect');
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        this.stompClient.subscribe('/topic/game-info', message => {
          this.game = JSON.parse(message.body)
        });
      });
    },
    selectType: function (event) {
      if (event.target.value === "1") {
        this.wantJoin = false
        this.wantCreate = true
      } else {
        this.wantCreate = false
        this.wantJoin = true
      }
    },
    create: function () {
      const email = this.$el.querySelector('#email').value;
      const requestToCreateGame = JSON.stringify({"email": email, "name": email});
      this.stompClient.send("/ws/create-game", {}, requestToCreateGame);
      this.isStarted = true
    },
    join: function () {
      const email = this.$el.querySelector('#email').value;
      const gameId = this.$el.querySelector('#game-id').value;
      const requestToJoin = JSON.stringify({
        "uuid": gameId,
        "user": {"email": email, "name": email}
      })
      this.stompClient.send("/ws/join-game", {}, requestToJoin);
      this.isStarted = true
    },
    start: function () {
      this.stompClient.send("/ws/start", {}, JSON.stringify(this.game));
    }

  }
}
</script>

<style scoped>
.ct {
  margin-top: 250px;
}

.main {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
</style>
