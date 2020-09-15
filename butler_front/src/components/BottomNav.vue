<template>
  <v-flex class="text-center mx-auto">
    <!-- <v-flex
      xs12
      class="text-center mx-auto justify-center align-center"
      style="max-width: 425px !important; width: 425px;"
    >
      <v-app-bar
        dense
        flat
        color="rgba(0, 0, 0, 0)"
        style="position: absolute; z-index: 70000; max-width: 425px; left: 20vw; bottom: 5px; width: 100% !important;"
        class="mx-auto text-center align-center justify-center"
      >
        <v-flex d-flex class="px-0 pt-0 mx-auto text-center align-center">
          <v-flex v-for="subLink in subLinks" :key="subLink.title">
            <v-btn :to="subLink.to" icon tile>
              <v-flex
                class="d-flex flex-column align-center"
                style="position: absolute; animation: mymove2; animation-duration: 1s"
              >
                <v-avatar class="ma-0 pa-0" color="#b5c7d3">
                  <img
                    :src="require(`@/assets/${subLink.fileName}`)"
                    style="width: 55%; height: 55%"
                  />
                </v-avatar>
                <p class="ma-0 pa-0 caption" style="color: #000000">{{ subLink.title }}</p>
              </v-flex>
            </v-btn>
          </v-flex>
        </v-flex>
      </v-app-bar>
    </v-flex> -->
    <v-flex>
      <v-app-bar
        dense
        flat
        bottom
        color="white"
        style="position: fixed; z-index: 10000; max-width: 425px;"
        class="mx-auto"
      >
        <v-flex d-flex class="px-0 pb-3 mt-3">
          <v-flex v-for="link in links" :key="link.title">
            <v-flex
              v-if="$route.fullPath == '/main' || $route.fullPath == '/breed' || $route.fullPath == '/study' || $route.fullPath == '/question'"
            >
              <v-btn icon tile :to="link.to" :color="changeColor(link.to)">
                <v-flex class="d-flex flex-column align-center">
                  <v-icon size="24px">{{ link.icon }}</v-icon>
                  <p class="ma-0 caption">{{ link.title }}</p>
                </v-flex>
              </v-btn>
            </v-flex>
            <v-flex v-else>
              <v-btn v-if="link.title == '홈'" :to="link.to" icon tile>
                <v-flex class="d-flex flex-column align-center">
                  <v-icon size="24px">{{ link.icon }}</v-icon>
                  <p class="ma-0 caption">{{ link.title }}</p>
                </v-flex>
              </v-btn>
              <v-btn
                v-if="link.title != '홈'"
                icon
                tile
                @click="setPath(link.title)"
                :color="link.title === path ? '#658DC6' : '#84898C'"
              >
                <v-flex class="d-flex flex-column align-center">
                  <v-icon size="24px">{{ link.icon }}</v-icon>
                  <p class="ma-0 caption">{{ link.title }}</p>
                </v-flex>
              </v-btn>
            </v-flex>
          </v-flex>
        </v-flex>
      </v-app-bar>
    </v-flex>
  </v-flex>
</template>

<script>
export default {
  name: "BottomNav",
  watch: {
    subLinks(e) {
      console.log(e);
    },
    path(e) {
      console.log(e);
    }
  },
  data() {
    return {
      path: "",
      subLinks: [],
      links: [
        {
          title: "홈",
          to: "/main",
          icon: "fa-home"
        },
        {
          title: "키워봐요",
          to: "/breed",
          icon: "mdi-dog-side"
        },
        {
          title: "공부해요",
          to: "/study",
          icon: "mdi-lead-pencil"
        },
        {
          title: "궁금해요",
          to: "/question",
          icon: "mdi-help-circle"
        }
      ]
    };
  },
  methods: {
    setPath(title) {
      this.path = title;

      this.subLinks = [];

      if (title === "키워봐요") {
        this.subLinks = [
          {
            title: "데일리 미션",
            to: "/missionlistpage",
            fileName: "dailyMission.png"
          },
          {
            title: "유기 동물",
            to: "/strayanimal",
            fileName: "유기동물_1.png"
          }
        ];
      } else if (title === "공부해요") {
        this.subLinks = [
          {
            title: "퀴즈 풀기",
            to: "/quiz",
            fileName: "퀴즈_2.png"
          },
          {
            title: "다시 풀기",
            to: "/requiz",
            fileName: "퀴즈다시풀기_3.png"
          },
          {
            title: "학습하기",
            to: "/learn",
            fileName: "학습하기.png"
          }
        ];
      } else if (title === "궁금해요") {
        this.subLinks = [
          {
            title: "양육 정보",
            to: "/quiz",
            fileName: "양육정보.png"
          },
          {
            title: "건강 정보",
            to: "/requiz",
            fileName: "건강정보.png"
          },
          {
            title: "행동 정보",
            to: "/learn",
            fileName: "행동정보.png"
          }
        ];
      }
    },
    changeColor(to) {
      if (this.$route.fullPath === to) {
        return "#658DC6";
      } else {
        return "#84898C";
      }
      // return "#658dc6";
    },
    mounted() {
      this.subLinks = [];
    }
  }
};
</script>

<style>
.v-btn:before {
  background-color: white;
}

@keyframes mymove2 {
  from {
    bottom: -100px;
  }
  to {
    bottom: -34px;
  }
}
</style>