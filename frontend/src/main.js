import Vue from 'vue'
import App from './App.vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Main from "@/components/Main";
import VueRouter from "vue-router";
import About from "@/components/About";

Vue.config.productionTip = false

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Main},
    {path: "/about", component: About}
]

const router = new VueRouter({
    routes,
    mode: "history"
})

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
