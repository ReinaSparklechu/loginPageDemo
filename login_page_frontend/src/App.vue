<template>
  <div id="app">
    <div id="buttonBar">
      <button @click.prevent="englishLocale">English</button>
      <button @click.prevent="chineseLocale">中文</button>
    </div>
    <router-view/>
  </div>


</template>

<script setup>
import {onMounted} from "vue";
import router from "@/components/router";
import {userDetailStore} from "@/stores/userDetail";
import {i18n} from "@/components/i18n";


onMounted(()=> {
  if(userDetailStore().$state.userDetail== undefined){
    router.push('/login')
  }
})
function chineseLocale(){
  i18n.global.locale = 'zh'
}
function englishLocale(){
  i18n.global.locale = 'en'
}

</script>

<style>
:root{
  --isabelline: #edede9ff;
  --pale-silver: #d6ccc2ff;
  --linen: #f5ebe0ff;
  --champagne-pink: #e3d5caff;
  --desert-sand: #d5bdafff;
}
*{
  padding: 0;
  margin: 0;
}
#app {
  display: flex;
  flex-direction: column;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: var(--isabelline) ;
  height: 100vh;
  width: 100vw;
  align-items: center;
  justify-content: center;
}
#buttonBar{
  display: flex;
  flex-direction: row;
  flex-basis: auto;
  align-content: space-evenly;
  justify-content: center;
  margin-bottom: 5%;
  border: 2px solid var(--champagne-pink);
  width: 20%;
  padding: 20px;
}
button{
  padding: 5px;
  margin-left:20px;
  margin-right: 20px;
  width: 20%;
}

</style>
