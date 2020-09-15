<template>
  <v-app id="inspire">
    <v-content>
      <v-flex
        v-if="$route.name === 'main' || $route.name === 'breed' || $route.name === 'study' || $route.name === 'question'"
      >
        <!-- <v-flex v-if="$route.name != 'login' && $route.name != 'ReadySteppersPage'"> -->
        <Header />
      </v-flex>
      <v-flex :style="checkloggedIn"></v-flex>
      <router-view :key="$route.fullPath"></router-view>
      <v-flex
        v-if="$route.name === 'main' || $route.name === 'breed' || $route.name === 'study' || $route.name === 'question'"
        class="text-center justify-center align-center d-flex"
      >
        <!-- <v-flex
        v-if="$route.name != 'login' && $route.name != 'ReadySteppersPage' && $route.name != 'NurtureChatPage'"
        class="text-center justify-center align-center d-flex"
        >-->
        <!-- <v-flex
          style="position: absolute; bottom: 120px;"
          v-if="$route.name === 'breed' || $route.name === 'study' || $route.name === 'question'"
        >-->
        <v-flex style="position: absolute; bottom: 120px;">
          <SubBottomNav :links="links" />
        </v-flex>
        <v-flex style="position: absolute; bottom: 50px">
          <BottomNav />
        </v-flex>
      </v-flex>
    </v-content>
  </v-app>
</template>

<script>
import store from "./store";
import Header from "./components/Header";
import SubBottomNav from "./components/SubBottomNav";
import BottomNav from "./components/BottomNav";
import router from "./router";

export default {
  name: "App",
  store,
  components: {
    Header,
    SubBottomNav,
    BottomNav
  },
  data() {
    return {
      test: document.location.href.split("/")[3],
      links: []
    };
  },
  watch: {
    $route(e) {
      this.links = [];

      if (e.fullPath === "/breed") {
        this.links = [
          {
            title: "데일리 미션",
            to: "/missionlistpage",
            fileName: "dailyMission.png"
          },
          {
            title: "유기 동물",
            to: "/strayanimal",
            fileName: "strayanimal.png"
          }
        ];
      } else if (e.fullPath === "/study") {
        this.links = [
          {
            title: "퀴즈 풀기",
            to: "/quiz",
            fileName: "quiz_2.png"
          },
          {
            title: "다시 풀기",
            to: "/requiz",
            fileName: "requiz.png"
          },
          {
            title: "학습하기",
            to: "/learn",
            fileName: "learn.png"
          }
        ];
      } else if (e.fullPath === "/question") {
        this.links = [
          {
            title: "양육 정보",
            to: "/nurture",
            fileName: "nuture.png"
          },
          {
            title: "건강 정보",
            to: "/health",
            fileName: "health.png"
          },
          {
            title: "행동 정보",
            to: "/behavior",
            fileName: "behavior.png"
          }
        ];
      }
    }
  },
  computed: {
    checkloggedIn: function() {
      if (this.$store.getters.isLoggedIn != null) {
        return "display: none;";
      } else if (this.$route.name == "admin") {
        this.$router.push("/admin").catch(err => {});
      } else if (this.$route.name === "signup") {
        return "display: none";
      } else {
        this.$router.push("/").catch(err => {});
      }
    }
  },
  mounted() {
    var url = location.href;
    this.links = [];

    if (url.split("/")[3] === "breed") {
      this.links = [
        {
            title: "데일리 미션",
            to: "/missionlistpage",
            fileName: "dailyMission.png"
          },
          {
            title: "유기 동물",
            to: "/strayanimal",
            fileName: "strayanimal.png"
          }
      ];
    } else if (url.split("/")[3] === "study") {
      this.links = [
        {
            title: "퀴즈 풀기",
            to: "/quiz",
            fileName: "quiz_2.png"
          },
          {
            title: "다시 풀기",
            to: "/requiz",
            fileName: "requiz.png"
          },
          {
            title: "학습하기",
            to: "/learn",
            fileName: "learn.png"
          }
      ];
    } else if (url.split("/")[3] === "question") {
      this.links = [
        {
            title: "양육 정보",
            to: "/nurture",
            fileName: "nuture.png"
          },
          {
            title: "건강 정보",
            to: "/health",
            fileName: "health.png"
          },
          {
            title: "행동 정보",
            to: "/behavior",
            fileName: "behavior.png"
          }
      ];
    }
  }
};
</script>
