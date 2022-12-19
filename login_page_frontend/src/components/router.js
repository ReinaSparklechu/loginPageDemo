import {createRouter} from 'vue-router'
import {createWebHistory} from "vue-router";
import Login from '@/components/Login'
import userDetails from "@/components/UserDetails";
import userList from '@/components/UserList'

const routes =[
    {path:'/login', component:Login},
    {path:'/', redirect:'/login'},
    {path:'/userDetails', component: userDetails},
    {path:'/userList', component: userList}]

const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router