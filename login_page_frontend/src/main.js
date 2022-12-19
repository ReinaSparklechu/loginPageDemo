import { createApp } from 'vue'
import App from './App.vue'
import router from '@/components/router'
import {createPinia} from "pinia/dist/pinia";
import {i18n} from "@/components/i18n";
import axios from "axios";

axios.defaults.withCredentials = true


const pinia = createPinia();
const app = createApp(App).use(router).use(pinia).use(i18n)
app.use(router)
app.use(pinia)
app.use(i18n)
app.mount('#app')
