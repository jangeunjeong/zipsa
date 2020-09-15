<template>
  <v-app-bar
    dense
    flat
    color="rgba(0, 0, 0, 0)"
    top
    style="position: fixed; z-index: 5000; max-width: 425px;"
    class="mx-auto mt-4"
  >
    <v-flex xs12>
      <v-flex d-flex class="text-center my-auto">
        <v-flex
        class="my-auto"
        style="position: absolute; left: 40%; top: 12px"
          v-if="$route.name != 'main' && $route.name != 'breed' && $route.name != 'study' && $route.name != 'question'"
        >as;dfjlsdkajfkl</v-flex>
        <v-flex class="justify-right text-right">
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn v-on="on" icon class="ml-1">
                <v-avatar class="ma-0 pa-0" color="#FFFFFF">
                  <img :src="faceImg" style="width: 80%; height: 80%" />
                </v-avatar>
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(dropItem, index) in dropItems"
                :key="index"
                @click="dropItem.menuAction"
              >
                <v-list-item-title>{{ dropItem.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-flex>
      </v-flex>
    </v-flex>
  </v-app-bar>
</template>
​
<script>
import { mapGetters } from "vuex";

export default {
  name: "Header",
  data() {
    return {
      dropItems: [
        {
          title: "내상태",
          menuAction: action => {
            this.$router.push({
              path: "myhome"
            });
          }
        },
        {
          title: "로그아웃",
          menuAction: action => {
            this.$store.dispatch("logout");
            this.$router.push({
              name: "login"
            });
          }
        }
      ],
      faceImg: ""
    };
  },
  watch: {},
  computed: {
    ...mapGetters(["getUser"])
  },
  methods: {},
  mounted() {
    var fk_animal_id = this.$store.getters.getUser.fk_animal_id;

    if (fk_animal_id == 1) {
      this.faceImg =
        "https://lab.ssafy.com/s02-final/s02p31b206/raw/develop/img/animal_img/%EC%A7%84%EB%8F%97%EA%B0%9C_%EC%96%BC%EA%B5%B4%202.png";
    } else if (fk_animal_id == 2) {
      this.faceImg =
        "https://lab.ssafy.com/s02-final/s02p31b206/raw/develop/img/animal_img/%EC%82%BC%EC%83%89%EA%B3%A0%EC%96%91%EC%9D%B4_%EC%96%BC%EA%B5%B42.png";
    } else if (fk_animal_id == 3) {
      this.faceImg =
        "https://lab.ssafy.com/s02-final/s02p31b206/raw/develop/img/animal_img/%EB%A7%90%ED%8B%B0%EC%A6%88_%EC%96%BC%EA%B5%B42.png";
    } else if (fk_animal_id == 4) {
      this.faceImg =
        "https://lab.ssafy.com/s02-final/s02p31b206/raw/develop/img/animal_img/%EA%B3%A8%EB%93%A0%EB%A6%AC%ED%8A%B8%EB%A6%AC%EB%B2%84_%EC%96%BC%EA%B5%B42.png";
    } else if (fk_animal_id == 5) {
      this.faceImg =
        "https://lab.ssafy.com/s02-final/s02p31b206/raw/develop/img/animal_img/%EB%9F%AC%EC%8B%9C%EC%95%88%EB%B8%94%EB%A3%A8_%EC%96%BC%EA%B5%B42.png";
    } else if (fk_animal_id == 6) {
      this.faceImg =
        "https://lab.ssafy.com/s02-final/s02p31b206/raw/develop/img/animal_img/%ED%8E%98%EB%A5%B4%EC%8B%9C%EC%95%88_%EC%96%BC%EA%B5%B42.png";
    }
  }
};
</script>

<style scoped>
</style>