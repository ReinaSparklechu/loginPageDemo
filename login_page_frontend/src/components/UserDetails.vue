<template>
  <div id = main>
    <label for="name">{{$t('username') +": "}}</label>
    <div id="name" v-if="details != undefined">{{details.username}}</div>
    <label for="fullname">{{$t('fullname') + ": "}}</label>
    <div id="fullname" v-if="details != undefined">{{details.fullName}}</div>
    <br>
    <label for="authorities">{{$t('roles') +": "}}</label>
    <ul v-if="details != undefined" id="authorities">
      <li v-for="auth in details.authorities" :key="auth">{{auth.authority}}</li>
    </ul>
    <button @click.prevent="getUserList" v-if="details != undefined && auths.includes('MANAGER')">{{$t('viewUsers')}}</button>
    <br>
    <LogoutButton v-if="details!= undefined"></LogoutButton>
  </div>
</template>

<script setup>
import {userDetailStore} from "@/stores/userDetail";
import {onMounted, ref} from "vue";
import axios from "axios";
import router from "@/components/router";
import LogoutButton from  '@/components/LogoutButton'

const details = ref(undefined)
const auths = ref([])
function getUserList(){
  axios.get("https://loginpagedemo-25d91.as.r.appspot.com/userList",{withCredentials:true}).then(res=>{
    userDetailStore().$state.userList= res.data
    router.push("/userList")
  })
}
onMounted(()=>{
  if(userDetailStore().$state.userDetail === undefined){
    axios.get("https://loginpagedemo-25d91.as.r.appspot.com/userDetails",{withCredentials: true}).then((res)=>{
      userDetailStore().$state.userDetail = res.data;
    })
  }
  details.value = userDetailStore().$state.userDetail;
  for(var i=0; i<details.value.authorities.length;i++ ){
    auths.value.push(details.value.authorities[i].authority)
  }
})
</script>

<style scoped>
#main{
  display: flex;
  flex-direction:column ;
  justify-content: left;
  border: 4px solid var(--desert-sand);
  border-radius: 10px;
  width: 40%;
  padding: 20px;
  height: 40%;
  background-color: var(--champagne-pink);
}
#name, #fullname{
  display: flex;
  flex-direction: column;
  height: 10%;
  background-color: var(--isabelline);
  padding: 5px;
  justify-content: left;
  align-content: center;
  text-justify: auto;
  align-content: center;
}
p{
  height: min-content;
  display: inline;
}
ul{
  height: 80%;
}
label{
  padding-top: 2%;
}
button{
  height: 10%;
  width: 20%;
  justify-self:end;

}
</style>