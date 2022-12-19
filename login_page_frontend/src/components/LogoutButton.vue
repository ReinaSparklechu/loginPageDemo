<template>
<button @click.prevent="performLogout">{{$t('logout')}}</button>
</template>

<script setup>
import axios from "axios";
import {userDetailStore} from "@/stores/userDetail";
import router from "@/components/router";
function performLogout(){
  axios.post("https://loginpagedemo-25d91.as.r.appspot.com/logout",{}, {withCredentials:true}).catch(function (error){
    console.log(error)
    router.push("/login")
  }).then(() => {
    userDetailStore().userDetail = undefined;
    userDetailStore().userList = []
    router.push("/login");
  })

}
</script>

<style scoped>
button{
  justify-self: left;
  height: fit-content;
  width: 10%;
}

</style>