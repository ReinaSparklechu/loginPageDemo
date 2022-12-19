import {defineStore} from "pinia/dist/pinia";

export const userDetailStore = defineStore('userDetail', {
    state:()=>{
        return {userDetail: undefined,
        userList: []}
    }
})