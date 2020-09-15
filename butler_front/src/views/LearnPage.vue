<template>
  <v-container fluid style="max-width: 425px !important;" class="mx-auto pt-0">
    <v-layout row wrap fill-height>
      <v-flex xs12>
        <v-flex d-flex class="text-center py-2" style="background-color: #ffffff">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>학습하기</v-flex>
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
                  <v-flex class="pl-1" style="color: #84898c; font-size: 12px;">{{ breedInfo }}</v-flex>
                </v-flex>
              </v-flex>
              <v-flex style="max-width: 100px !important; height: 78px !important;">
                <img :src="img" style="width: 100%; height: 100%;" />
              </v-flex>
            </v-flex>
            <v-data-iterator :items="animal" hide-default-footer v-if="animalType == '개'">
              <template v-slot:default="props">
                <v-list class="py-0" dense v-for="item in props.items" :key="item.id">
                  <v-flex d-flex stlye="height: 10px !important">
                    <v-list-item>
                      <v-list-item-subtitle>털길이</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.hair_length }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>털종류</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.characteristics }}</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>크기</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.size }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>평균 수명</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.age }} (년)</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>산책 시간</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.exercise_requirements }} (분)</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>활동성</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.energy_level }}</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>침 흘리기</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.drool }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>코골이</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.snore }}</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>짖기</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.bark }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>땅 파기</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.dig }}</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>관심 필요성</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.social_needs }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle></v-list-item-subtitle>
                      <v-list-item-title class="text-right"></v-list-item-title>
                    </v-list-item>
                  </v-flex>
                </v-list>
              </template>
            </v-data-iterator>
            <v-data-iterator :items="animal" hide-default-footer v-if="animalType == '고양이'">
              <template v-slot:default="props">
                <v-list class="py-0" dense v-for="item in props.items" :key="item.id">
                  <v-flex d-flex stlye="height: 10px !important">
                    <v-list-item>
                      <v-list-item-subtitle>털길이</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.hair_length }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>털종류</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.characteristics }}</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>크기</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.size }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>평균 수명</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.age }} (년)</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>관심 필요성</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.social_needs }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>알레르기 유발</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.less_allergenic }}</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                  <v-flex d-flex>
                    <v-list-item>
                      <v-list-item-subtitle>그루밍 필요성</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.grooming_needs }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-subtitle>털빠짐</v-list-item-subtitle>
                      <v-list-item-title class="text-right">{{ item.shed }}</v-list-item-title>
                    </v-list-item>
                  </v-flex>
                </v-list>
              </template>
            </v-data-iterator>
          </v-flex>
          <v-divider></v-divider>
          <v-flex class="pa-3">
            <v-flex style="color: #658dc6; font-size: 17px; font-weight: bold">특징</v-flex>
            <v-data-iterator :items="animal" hide-default-footer>
              <template v-slot:default="props">
                <v-list class="pt-3" dense v-for="item in props.items" :key="item.id">
                  <v-list-item>
                    <v-list-item-title>
                      <v-flex v-html="item.feature"></v-flex>
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </template>
            </v-data-iterator>
          </v-flex>
          <v-divider></v-divider>
          <v-flex class="pa-3">
            <v-flex style="color: #658dc6; font-size: 17px; font-weight: bold">요약</v-flex>
            <v-data-iterator :items="animal" hide-default-footer>
              <template v-slot:default="props">
                <v-list class="pt-3" dense v-for="item in props.items" :key="item.id">
                  <v-list-item>
                    <v-list-item-title>
                      <v-flex v-html="item.summary"></v-flex>
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </template>
            </v-data-iterator>
          </v-flex>
          <v-divider></v-divider>
          <v-flex class="pa-3">
            <v-flex style="color: #658dc6; font-size: 17px; font-weight: bold">성격</v-flex>
            <v-data-iterator :items="animal" hide-default-footer>
              <template v-slot:default="props">
                <v-list class="pt-3" dense v-for="item in props.items" :key="item.id">
                  <v-list-item>
                    <v-list-item-title>
                      <v-flex v-html="item.personality"></v-flex>
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </template>
            </v-data-iterator>
          </v-flex>
          <v-divider></v-divider>
          <v-flex class="pa-3">
            <v-flex style="color: #658dc6; font-size: 17px; font-weight: bold">키울 때 주의할 점</v-flex>
            <v-data-iterator :items="animal" hide-default-footer>
              <template v-slot:default="props">
                <v-list class="pt-3" dense v-for="item in props.items" :key="item.id">
                  <v-list-item>
                    <v-list-item-title>
                      <v-flex v-html="item.living_with"></v-flex>
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </template>
            </v-data-iterator>
          </v-flex>
          <v-divider></v-divider>
          <v-flex class="pa-3">
            <v-flex style="color: #658dc6; font-size: 17px; font-weight: bold">역사</v-flex>
            <v-data-iterator :items="animal" hide-default-footer>
              <template v-slot:default="props">
                <v-list class="pt-3" dense v-for="item in props.items" :key="item.id">
                  <v-list-item>
                    <v-list-item-title>
                      <v-flex v-html="item.history"></v-flex>
                    </v-list-item-title>
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
    ...mapGetters(["getAxios", "getUser"])
  },
  data() {
    return {
      email: this.$store.getters.getUser.email,
      id: "",
      breedInfo: "",
      breed: "",
      img: "",
      animal: [],
      animalType: ""
    };
  },
  methods: {
    init() {
      this.getAxios
        .get("/study/learn/" + this.email)
        .then(res => {
          this.animal = res.data.animal;
          this.id = res.data.animal[0].id;
          this.breed = res.data.animal[0].breed;
          this.img = res.data.animal[0].img;
          console.log(res);
          //   this.breed = res.data.animal[0].breed;
        })
        .catch(err => {
          console.log(err);
        });
    },
    goBack() {
      this.$router.go(-1);
    },
    recognizeAnimal() {
      if (this.id == 1 || (this.id >= 3 && this.id <= 4)) {
        this.breedInfo = "[개] " + this.breed;
        this.animalType = "개";
        return 1;
      } else {
        this.breedInfo = "[고양이] " + this.breed;
        this.animalType = "고양이";
        return 2;
      }
    }
  },
  mounted() {
    this.init();
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

.v-list-item.theme--light {
  min-height: 25px !important;
}
</style>