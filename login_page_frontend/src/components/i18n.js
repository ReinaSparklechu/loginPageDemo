import {createI18n} from "vue-i18n/dist/vue-i18n.esm-bundler";

export const i18n = createI18n({
    locale:navigator.language,
    fallbackLocale: 'en',


    messages:{
        en:{
            login:"Login",
            password:"Password",
            username:"Username",
            roles:"Roles",
            viewUsers:"View Users",
            logout:"Logout",
            number:"No",
            fullname:"Full name"

        },
        zh:{
            login: "登录",
            password:"密码",
            username:"用户名",
            roles:"角色",
            viewUsers:"使用者表",
            logout:"登出",
            number:"编号",
            fullname:"姓名"
        },
    }
})