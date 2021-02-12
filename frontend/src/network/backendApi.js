import axios from "axios"
const headers = {
    'Content-Type': 'application/json'
}

export function sendCreateGame(data){
    axios.post(
        "http://localhost:8081/game/create",
        JSON.stringify(data),
        {
            headers: headers
        }
    )
}
