<template>
  <div id="game">
    <div class="player">
      <label> NAME:
        <input type="text" name="name">
      </label>
      <label> EMAIL:
        <input type="text" name="email">
      </label>
    </div>
    <input type="button" value="create game" @click="createGame()">
  </div>
</template>

<script>
import {User} from "@/user/user";
import {Game} from "@/user/game";
import {sendCreateGame} from "@/network/backendApi";

export default {
  name: 'GameCreator',
  props: {
    msg: String
  },
  computed: {
    addNewPlayer: function () {
      const allPlayers = document.getElementsByClassName("player");
      const lastPlayers = allPlayers[allPlayers.length - 1]


      const game = document.getElementById("game");
      game.appendChild(player)
    },
    methods: {
      createGame: function () {
        const players = document.querySelectorAll(".player");
        const test = [];

        players.forEach(player => {
          const name = player.querySelector("input[name=name]").value;
          const email = player.querySelector("input[name=email]").value;
          if (name !== null && email != null) {
            test.push(new User(name, email))
          }
        })

        const game = new Game(test)

        sendCreateGame(game)
      }
    }
  }
</script>
