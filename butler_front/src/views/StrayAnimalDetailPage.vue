<template>
  <v-container fluid style="max-width: 425px !important;" class="mx-auto pt-0">
    <v-layout row wrap fill-height>
      <v-flex xs12>
        <v-flex d-flex class="text-center py-2" style="background-color: #ffffff">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>센터 보호현황 보기</v-flex>
        </v-flex>
      </v-flex>
      <v-flex>
        <v-card tile outlined class="mb-3">
          <v-divider></v-divider>
          <v-flex class="pa-3">
            <v-flex style="color: #658dc6; font-size: 17px; font-weight: bold">동물 정보</v-flex>
            <v-flex d-flex class="pt-3 pb-0">
              <v-flex>
                <v-flex d-flex class="pb-2">
                  <v-icon color="#658dc6" size="18px" v-if="recognizeAnimal() == 1">mdi-dog</v-icon>
                  <v-icon color="#658dc6" size="18px" v-else>mdi-cat</v-icon>
                  <v-flex
                    class="pl-1"
                    style="color: #84898c; font-size: 12px;"
                  >{{ breed }} | {{ gender }}</v-flex>
                </v-flex>
              </v-flex>
              <v-flex style="max-width: 100px !important; height: 78px !important;">
                <img :src="img" style="width: 100%; height: 100%;" />
              </v-flex>
            </v-flex>
            <v-data-iterator :items="strayAnimal" hide-default-footer>
              <template v-slot:default="props">
                <v-list class="py-0" dense v-for="item in props.items" :key="item.id">
                  <v-list-item>
                    <v-list-item-subtitle>털색</v-list-item-subtitle>
                    <v-list-item-title>{{ item.color }}</v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-subtitle>체중</v-list-item-subtitle>
                    <v-list-item-title>{{ item.weight }} (Kg)</v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-subtitle>나이</v-list-item-subtitle>
                    <v-list-item-title>{{ item.age }} (년생)</v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-subtitle>공고기간</v-list-item-subtitle>
                    <v-list-item-title>{{ item.created_at }} ~ {{ item.deleted_at }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </template>
            </v-data-iterator>
          </v-flex>
          <v-divider></v-divider>
          <v-flex class="pa-3">
            <v-flex style="color: #658dc6; font-size: 17px; font-weight: bold">동물보호기관</v-flex>
            <v-data-iterator :items="strayAnimal" hide-default-footer>
              <template v-slot:default="props">
                <v-list class="py-0" dense v-for="item in props.items" :key="item.id">
                  <v-list-item>
                    <v-list-item-subtitle>기관명</v-list-item-subtitle>
                    <v-list-item-title>{{ item.center_name }}</v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-subtitle>연락처</v-list-item-subtitle>
                    <v-list-item-title>{{ item.tel }}</v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-subtitle>위치</v-list-item-subtitle>
                    <v-list-item-title>{{ item.address }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </template>
            </v-data-iterator>
          </v-flex>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["getAxios"])
  },
  data() {
    return {
      id: window.sessionStorage.getItem("strayAnimalId"),
      breed: "",
      gender: "",
      img: "",
      strayAnimal: []
    };
  },
  methods: {
    recognizeAnimal() {
      var tmpBreed = window.sessionStorage
        .getItem("detailBreed")
        .split(" ")[0]
        .replace("[", "")
        .replace("]", "");

      if (tmpBreed == "개") {
        return 1;
      } else if (tmpBreed == "고양이") {
        return 2;
      }
    },
    goBack() {
      this.$router.go(-1);
    },
    init() {
      this.getAxios
        .get("/breed/strayanimal/detail/" + this.id)
        .then(res => {
          this.strayAnimal = res.data.strayAnimal;
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  mounted() {
    this.getAxios
      .get("/breed/strayanimal/detail/" + this.id)
      .then(res => {
        this.strayAnimal = res.data.strayAnimal;
        this.breed = this.strayAnimal[0].breed;
        this.gender = this.strayAnimal[0].gender;
        this.img = this.strayAnimal[0].img;
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>
<style>
.v-card {
  width: 100%;
}

.card::-webkit-scrollbar {
  display: none;
}

.card {
  -ms-overflow-style: none;
}

.v-list-item__title {
  overflow: none;
  text-overflow: unset;
  white-space: unset;
}
</style>