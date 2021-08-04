import Vue from 'vue'
import App from './App.vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Main from "@/components/Main";
import VueRouter from "vue-router";
import Information from "@/components/Information";

Vue.config.productionTip = false

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

Vue.use(VueRouter)

const routes = [
    {path: '/game', component: Main},
    {path: '/information', component: Information}
]

const router = new VueRouter({
    routes,
    mode: "history"
})

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
