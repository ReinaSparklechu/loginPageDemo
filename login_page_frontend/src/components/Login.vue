<template>
  <div id="main">
    <form @submit.prevent="getUser">
      <label for="Username">{{$t('username') +": "}}</label>
      <input type="text" name="Username" v-model="username">
      <br>
      <label for="Password">{{$t('password') +": "}}</label>
      <input type="password" name="Password" v-model="password">
      <button @click.prevent="getUser">{{$t('login')}}</button>
    </form>

  </div>
</template>

<script setup>
import {ref} from "vue"
import axios from "axios"
import {userDetailStore} from "@/stores/userDetail";
import router from "@/components/router";

const user = ref(new Object);
function getUser(){
  user.value= axios.get("https://loginpagedemo-25d91.as.r.appspot.com/userDetails",{headers:{Authorization:'Basic ' + btoa(username.value+ ':' +password.value)}, withCredentials:true}).catch(function (err){
    if(err.response.status == 401){
      alert("Incorrect username/password")
    }
  }).then(response=>{
    if(response.status == 200){
      userDetailStore().userDetail = response.data;
      router.push("/userDetails");
    }
  })
}
const username = ref('');
const password = ref('');
</script>

<style scoped>
#main{
  display: flex;
  flex-direction:column ;
  justify-content: left;
  border: 4px solid var(--desert-sand);
  border-radius: 10px;
  width: 40%;
  padding: 10px;
  height: 20%;
  background-color: var(--champagne-pink);
}
input{
  margin-top: 1vh;
  margin-bottom: 1vh;
  height: 4vh;
  width: 100%;
}
button{
  width: 20%;
  height: 20%;
}
</style>