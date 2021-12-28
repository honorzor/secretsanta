const Stomp = require("stompjs");
const SockJS = require("sockjs-client");

export var stompClient = null;


export function joinGame(email) {
    stompClient.send("/ws/join-game", {}, JSON.stringify({"uuid": email}));
}

export function createGame(email) {
    stompClient.send("/ws/create-game", {}, JSON.stringify({"email": email}));
}

export function connect() {
    const socket = new SockJS('http://localhost:8081/ws-connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game-info', function (message) {
            console.log(message)
        });
    });
}