<template>
  <v-container fluid grid-list-xl class="login-bg">
    <v-layout row wrap>
      <v-flex d-flex>
        <v-flex>
          <v-flex
            xs12
            class="mt-12 pt-12 justify-center text-center justify-center"
            style="position: relative; top: 53vh"
          >
            <img
              src="../assets/kakaoLogin.png"
              width="70%"
              height="48px;"
              style="cursor: pointer"
              @click="goKakao"
            />
            <v-flex class="pt-0">
              <router-link to="/main" style="color: #ffffff; text-decoration: none;">
                <span style="font-size: 13px">앱 둘러보기</span>
              </router-link>
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "LoginPage",
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["requestHeader", "user", "getAxios"])
  },
  methods: {
    goKakao() {
      window.location.href =
        "https://kauth.kakao.com/oauth/authorize?client_id=7a25be4a428f95fd1bd16ecc1d6ca59b&redirect_uri=http://localhost:8080&response_type=code";
    }
  },
  mounted() {
    if (this.$route.query.code) {
      this.getAxios
        .get("/user/kakao_oauth?code=" + this.$route.query.code)
        .then(res => {
          if (res.data.log == "회원가입이 필요합니다.") {
            this.$router.push({
              name: "ReadySteppersPage", // 사전조사
              params: {
                // data: res.data
                email: res.data.info.email,
                nickname: res.data.info.nickname
              }
            });
          } else if (res.data.status == true) {
            let loginData = {
              //id: res.data.info.id,
              //token: res.data["jwt-auth-token"],
              accessToken: res.data["access-token"], // 카카오토큰
              email: res.data.info.email,
              nickname: res.data.info.nickname,
              fk_animal_id: res.data.info.fk_animal_id
            };

            //this.$store.commit("startLogin", loginData);
            //this.$store.commit("isLogin");
            //cookie.cookieCreate(loginData);

            this.$store.dispatch("startLogin", loginData);
            // this.$store.dispatch("login", res.data["access-token"]);
            //  /   this.$store.dispatch("login",loginData.accessToken );

            this.$router.push("/main");
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (vm.$store.getters.isLoggedIn) {
        next({ path: "/main" });
      } else {
        next();
      }
    });
  }
};
</script>
<style>
.login-bg {
  max-width: 425px !important;
  height: 100vh !important;
  background-size: 80vh !important;
  background: url("../assets/mainZipsa.png") no-repeat right top fixed;
  background-position: center;
}
</style>