<template>
<div id="main">
  <table v-if="showList">
    <tr>
      <th>{{$t('number')}}</th>
      <th>{{$t('username')}}</th>
      <th>{{$t('fullname')}}</th>
      <th>{{$t('roles')}}</th>
    </tr>
    <tr v-for="user in userList" :key="user.name">
      <td>{{userList.indexOf(user) + 1}}</td>
      <td>{{user.username}}</td>
      <td>{{user.fullName}}</td>
      <td>{{authList(user)}}</td>
    </tr>
  </table>
  <p v-else>You do not have the permissions to view this page.
    <br>
    Returning to login page</p>
  <LogoutButton v-if="details != undefined"></LogoutButton>
</div>
</template>

<script setup>
import {userDetailStore} from "@/stores/userDetail";
import {onMounted, ref, computed} from "vue";
import router from "@/components/router";
import LogoutButton from "@/components/LogoutButton"
const userList = computed(() => {
  return userDetailStore().userList
})
const details = ref()
const auths = ref([])
const showList = ref(true);
onMounted(() => {
  details.value = userDetailStore().$state.userDetail
  for(var i=0; i<userDetailStore().$state.userDetail.authorities.length;i++ ){
    auths.value.push(userDetailStore().$state.userDetail.authorities[i].authority)
  }
  if(!auths.value.includes('MANAGER')){
    showList.value= false;
    setTimeout(()=> {
      router.push("/login")
    }, 3000)
  }
})

function authList(user){
  var authString = ""
  for(var i = 0; i<user.authorityList.length; i++){
    authString = authString.concat(user.authorityList[i]);
    authString = authString.concat(",");
  }
  authString = authString.substring(0,authString.length-1);
  return authString
}
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
  height: fit-content;
  background-color: var(--champagne-pink);
}
th{
  text-align: left;
  background-color: var(--desert-sand);
  padding: 5px;
}
td{
  padding: 5px;
  background-color:var(--linen);
  border:2px solid var(--pale-silver);
}
</style>