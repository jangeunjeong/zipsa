<template>
  <v-container fluid style="max-width: 425px !important;" class="mx-auto">
    <v-layout row wrap fill-height>
      <v-flex xs12>
        <v-flex d-flex class="text-center py-2" style="background-color: #ffffff">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>유기동물 가족만들기</v-flex>
        </v-flex>
        <v-divider></v-divider>
      </v-flex>
      <v-flex xs12 style="background-color: #ffffff">
        <v-flex class="pa-3">보호소 동물 조회</v-flex>
        <v-divider></v-divider>
      </v-flex>
      <v-flex xs12 style="background-color: #ffffff">
        <v-flex d-flex xs12 class="py-0">
          <v-flex
            d-flex
            class="pa-0 ma-0 card"
            style="max-height: 36px !important; max-width: 335px !important; overflow-x: auto;"
          >
            <v-btn
              @click="toggleAddress"
              depressed
              text
              class="px-0"
              style="font-size: 12px"
              :color="changeColor(addressFlag)"
            >
              {{ centerName }}
              <v-icon v-if="!addressFlag" style="font-size: 20px">mdi-chevron-down</v-icon>
              <v-icon v-if="addressFlag" style="font-size: 20px">mdi-chevron-up</v-icon>
            </v-btn>
            <v-btn
              @click="toggleBreed"
              depressed
              text
              class="px-0"
              style="font-size: 12px"
              :color="changeColor(breedFlag)"
            >
              {{ breed }}
              <v-icon v-if="!breedFlag" style="font-size: 20px">mdi-chevron-down</v-icon>
              <v-icon v-if="breedFlag" style="font-size: 20px">mdi-chevron-up</v-icon>
            </v-btn>
            <v-btn
              @click="toggleBreedDate"
              depressed
              text
              class="px-0"
              style="font-size: 12px"
              :color="changeColor(breedDateFlag)"
            >
              {{ startAt }} ~ {{ endAt }}
              <v-icon v-if="!breedDateFlag" style="font-size: 20px">mdi-chevron-down</v-icon>
              <v-icon v-if="breedDateFlag" style="font-size: 20px">mdi-chevron-up</v-icon>
            </v-btn>
          </v-flex>
          <v-flex d-flex class="mx-auto my-auto">
            <v-divider vertical></v-divider>
            <v-flex class="text-center">
              <v-btn @click="init()" text depressed>
                <v-icon>mdi-refresh</v-icon>
              </v-btn>
            </v-flex>
          </v-flex>
        </v-flex>
        <v-divider></v-divider>
      </v-flex>
      <v-dialog
        v-model="addressFlag"
        max-height="140px"
        max-width="425px"
        hide-overlay
        class="ma-0"
      >
        <v-card>
          <v-row no-gutters>
            <v-col>
              <v-card outlined tile class="text-center">
                <v-flex>
                  <v-flex style="font-size: 12px; background-color: #e8eaed;">
                    시/도
                    <v-divider></v-divider>
                  </v-flex>
                  <v-flex style="height: 120px; overflow-y: scroll" class="card">
                    <v-btn
                      text
                      v-for="city in cityList"
                      :key="city"
                      style="width: 100%; height: 24px"
                      class="pa-1"
                      @click="selectCity(city)"
                      :style="changeStyleCity(city)"
                    >{{ city }}</v-btn>
                  </v-flex>
                </v-flex>
              </v-card>
            </v-col>
            <v-col>
              <v-card outlined tile class="text-center">
                <v-flex>
                  <v-flex style="font-size: 12px; background-color: #e8eaed;">
                    시/군/구
                    <v-divider></v-divider>
                  </v-flex>
                  <v-flex style="height: 120px; overflow-y: scroll" class="card">
                    <v-btn
                      text
                      v-for="town in townList"
                      :key="town"
                      style="width: 100%; height: 24px"
                      class="pa-1"
                      @click="selectTown(town)"
                      :style="changeStyleTown(town)"
                    >{{ town }}</v-btn>
                  </v-flex>
                </v-flex>
              </v-card>
            </v-col>
            <v-col>
              <v-card outlined tile class="text-center">
                <v-flex>
                  <v-flex style="font-size: 12px; background-color: #e8eaed;">
                    보호소
                    <v-divider></v-divider>
                  </v-flex>
                  <v-flex style="height: 120px; overflow-y: scroll" class="card pa-0">
                    <v-btn
                      text
                      v-for="centerOne in centerNameList"
                      :key="centerOne"
                      style="width: 100%; height: 24px;"
                      class="pa-1"
                      @click="selectCenterName(centerOne)"
                      :style="changeStyleCenterName(centerOne)"
                    >{{ centerOne }}</v-btn>
                  </v-flex>
                </v-flex>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
      </v-dialog>
      <v-dialog v-model="breedFlag" max-height="140px" max-width="425px" hide-overlay class="ma-0">
        <v-card>
          <v-row no-gutters>
            <v-col cols="4">
              <v-card outlined tile class="text-center">
                <v-flex>
                  <v-flex style="font-size: 12px; background-color: #e8eaed;">
                    동물
                    <v-divider></v-divider>
                  </v-flex>
                  <v-flex style="height: 120px; overflow-y: scroll" class="card">
                    <v-btn
                      text
                      v-for="animal in animalList"
                      :key="animal"
                      style="width: 100%; height: 24px"
                      @click="selectAnimal(animal)"
                      :style="changeStyleAnimal(animal)"
                    >{{ animal }}</v-btn>
                  </v-flex>
                </v-flex>
              </v-card>
            </v-col>
            <v-col cols="8">
              <v-card outlined tile class="text-center">
                <v-flex>
                  <v-flex style="font-size: 12px; background-color: #e8eaed;">
                    품종
                    <v-divider></v-divider>
                  </v-flex>
                  <v-flex style="height: 120px; overflow-y: scroll" class="card">
                    <v-btn
                      text
                      v-for="breedOne in breedList"
                      :key="breedOne"
                      style="width: 100%; height: 24px"
                      @click="selectBreed(breedOne)"
                      :style="changeStyleBreed(breedOne)"
                    >{{ breedOne }}</v-btn>
                  </v-flex>
                </v-flex>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
      </v-dialog>
      <v-dialog
        v-model="breedDateFlag"
        hide-overlay
        max-height="180px"
        max-width="425px"
        class="ma-0"
      >
        <v-card>
          <v-tabs background-color="#ffffff" color="#658dc6" height="38px">
            <v-tab class="font-weight-bold" style="width: 100%;" @click="toggleTab(1)">공고 시작일</v-tab>
            <v-tab class="font-weight-bold" style="width: 100%;" @click="toggleTab(0)">공고 종료일</v-tab>
            <v-tab-item>
              <v-flex style="height: 120px;" class="card text-center pa-3">
                <date-picker
                  v-model="tmpStartAt"
                  type="date"
                  value-type="format"
                  format="YYYY.MM.DD"
                  style="top: 20%"
                  :open.sync="startOpen"
                  @change="handleChange()"
                ></date-picker>
              </v-flex>
            </v-tab-item>
            <v-tab-item>
              <v-flex style="height: 120px;" class="card text-center pa-3">
                <v-flex>
                  <date-picker
                    v-model="tmpEndAt"
                    type="date"
                    value-type="format"
                    format="YYYY.MM.DD"
                    style="top: 20%"
                    :open.sync="endOpen"
                  ></date-picker>
                </v-flex>
                <v-flex class="pt-3">
                  <v-btn outlined @click="setBreedDate()">적용</v-btn>
                </v-flex>
              </v-flex>
            </v-tab-item>
          </v-tabs>
        </v-card>
      </v-dialog>
      <!-- <v-flex style="background-color: #f5f5f5"> -->
      <v-flex>
        <v-card
          tile
          outlined
          v-for="strayAnimal in strayAnimalList"
          :key="strayAnimal.id"
          class="mb-3"
          @click="goDetail(strayAnimal.id, strayAnimal.breed)"
        >
          <v-flex d-flex class="pa-3">
            <v-flex>
              <v-flex d-flex class="pb-2">
                <v-icon
                  color="#658dc6"
                  size="18px"
                  v-if="recognizeAnimal(strayAnimal.breed) == 1"
                >mdi-dog</v-icon>
                <v-icon
                  color="#658dc6"
                  size="18px"
                  v-if="recognizeAnimal(strayAnimal.breed) == 2"
                >mdi-cat</v-icon>
                <v-flex
                  class="pl-1"
                  style="color: #84898c; font-size: 12px;"
                >{{ strayAnimal.breed }} | {{ strayAnimal.gender }}</v-flex>
              </v-flex>
              <v-flex style="font-size: 14px;">{{ strayAnimal.center_name }}</v-flex>
              <v-flex
                style="color: #84898c; font-size: 12px;"
                class="pt-2"
              >{{ strayAnimal.created_at }} ~ {{ strayAnimal.deleted_at }}</v-flex>
            </v-flex>
            <v-flex style="max-width: 100px !important; height: 78px !important;">
              <img :src="strayAnimal.img" style="width: 100%; height: 100%;" />
            </v-flex>
          </v-flex>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";

export default {
  computed: {
    ...mapGetters(["getAxios"])
  },
  components: { DatePicker },
  data() {
    return {
      addressFlag: false,
      breedFlag: false,
      breedDateFlag: false,
      centerName: "지역",
      breed: "품종",
      startAt: "",
      endAt: "",
      selectedCity: "전체",
      selectedTown: "전체",
      selectedCenterName: "전체",
      selectedAnimal: "전체",
      selectedBreed: "전체",
      tmpStartAt: "",
      tmpEndAt: "",
      nowTabIdx: 0,
      startOpen: false,
      endOpen: false,
      cityList: [
        "전체",
        "서울특별시",
        "부산광역시",
        "대구광역시",
        "인천광역시",
        "광주광역시",
        "세종특별자치시",
        "대전광역시",
        "울산광역시",
        "경기도",
        "강원도",
        "충청북도",
        "충청남도",
        "전라북도",
        "전라남도",
        "경상북도",
        "경상남도",
        "제주특별자치도"
      ],
      townList: ["전체"],
      centerNameList: ["전체"],
      animalList: ["전체", "개", "고양이"],
      breedList: ["전체"],
      strayAnimalList: [],
      tabs: [
        {
          index: 0,
          content: ""
        },
        {
          index: 1,
          content: ""
        }
      ]
    };
  },
  methods: {
    makeEndAt() {
      var d = new Date();
      var year = d.getFullYear();
      var month =
        d.getMonth() >= 9 ? d.getMonth() + 1 : "0" + (d.getMonth() + 1);
      var day = d.getDate() >= 10 ? d.getDate() : "0" + d.getDate();
      this.endAt = year + "." + month + "." + day;
      d = new Date(year, month, day);
      return d;
    },
    makeStartAt(d) {
      var year = d.getFullYear();
      d.setMonth(d.getMonth() - 1);
      var month = d.getMonth() >= 10 ? d.getMonth() : "0" + d.getMonth();
      var day = d.getDate() >= 10 ? d.getDate() : "0" + d.getDate();
      this.startAt = year + "." + month + "." + day;
    },
    makeDate() {
      this.makeStartAt(this.makeEndAt());
    },
    toggleAddress() {
      this.breedFlag = false;
      this.breedDateFlag = false;

      if (this.addressFlag) {
        this.addressFlag = false;
      } else if (!this.addressFlag) {
        this.addressFlag = true;
      }
    },
    toggleBreed() {
      this.addressFlag = false;
      this.breedDateFlag = false;

      if (this.breedFlag) {
        this.breedFlag = false;
      } else if (!this.breedFlag) {
        this.breedFlag = true;
      }
    },
    toggleBreedDate() {
      this.addressFlag = false;
      this.breedFlag = false;

      if (this.breedDateFlag) {
        this.breedDateFlag = false;
      } else if (!this.breedDateFlag) {
        this.breedDateFlag = true;
      }
    },
    changeColor(flag) {
      if (!flag) {
        return "#84898c";
      } else if (flag) {
        return "#658dc6";
      }
    },
    selectCity(city) {
      this.selectedCity = city;
      this.townList = ["전체"];

      this.getAxios
        .get("/breed/strayanimal/townlist/" + city)
        .then(res => {
          this.townList = this.townList.concat(res.data.townList);
        })
        .catch(err => {
          console.log(err);
        });
    },
    selectTown(town) {
      this.selectedTown = town;
      var address =
        town == "전체" ? this.selectedCity : this.selectedCity + " " + town;
      this.centerNameList = ["전체"];

      this.getAxios
        .get("/breed/strayanimal/centerlist/" + address)
        .then(res => {
          this.centerNameList = this.centerNameList.concat(
            res.data.centerNameList
          );
        })
        .catch(err => {});
    },
    selectCenterName(centerOne) {
      this.selectedCenterName = centerOne;
      this.centerName = centerOne;
      this.addressFlag = false;

      var keyword = {
        breed: this.breed == "품종" ? "" : this.breed,
        created_at: this.startAt.replace(/\./g, "-"),
        deleted_at: this.endAt.replace(/\./g, "-"),
        address:
          this.selectedCity == "전체" && this.selectedTown == "전체"
            ? ""
            : this.selectedCity + " " + this.selectedTown,
        center_name: this.centerName == "지역" ? "" : this.centerName
      };

      this.getStrayAnimalList(keyword);
    },
    selectAnimal(animal) {
      this.selectedAnimal = animal;

      var keyword = {
        breed: animal,
        created_at: this.startAt.replace(/\./g, "-"),
        deleted_at: this.endAt.replace(/\./g, "-"),
        address:
          this.selectedCity == "전체" && this.selectedTown == "전체"
            ? ""
            : this.selectedCity + " " + this.selectedTown,
        center_name: this.centerName == "지역" ? "" : this.centerName
      };

      this.getAxios
        .post("/breed/strayanimal/breed", keyword)
        .then(res => {
          this.breedList = ["전체"];
          this.breedList = this.breedList.concat(res.data.breedList);
        })
        .catch(err => {
          console.log(err);
        });
    },
    selectBreed(breedOne) {
      this.selectedBreed = breedOne;
      this.breed = breedOne;
      this.breedFlag = false;

      var keyword = {
        breed: "[" + this.selectedAnimal + "]" + breedOne,
        created_at: this.startAt.replace(/\./g, "-"),
        deleted_at: this.endAt.replace(/\./g, "-"),
        address:
          this.selectedCity == "전체" && this.selectedTown == "전체"
            ? ""
            : this.selectedCity + " " + this.selectedTown,
        center_name: this.centerName == "지역" ? "" : this.centerName
      };

      this.getStrayAnimalList(keyword);
    },
    changeStyleCity(city) {
      if (this.selectedCity == city) {
        return "font-weight: bold; color: #000000;";
      } else {
        return "color: #89848c;";
      }
    },
    changeStyleTown(town) {
      if (this.selectedTown == town) {
        return "font-weight: bold; color: #000000;";
      } else {
        return "color: #89848c;";
      }
    },
    changeStyleCenterName(centerName) {
      if (this.selectedCenterName == centerName) {
        return "font-weight: bold; color: #000000;";
      } else {
        return "color: #89848c;";
      }
    },
    changeStyleAnimal(animal) {
      if (this.selectedAnimal == animal) {
        return "font-weight: bold; color: #000000;";
      } else {
        return "color: #89848c;";
      }
    },
    changeStyleBreed(breedOne) {
      if (this.selectedBreed == breedOne) {
        return "font-weight: bold; color: #000000;";
      } else {
        return "color: #89848c;";
      }
    },
    toggleTab(tabIdx) {
      if (tabIdx == 0) {
        this.nowTabIdx = 1;
      } else {
        this.nowTabIdx = 0;
      }
      this.handleChange(tabIdx);
    },
    handleChange(tabIdx) {
      if (this.nowTabIdx != tabIdx) {
        this.startOpen = false;
        this.endOpen = false;
      }
    },
    recognizeAnimal(breed) {
      var tmpBreed = breed
        .split(" ")[0]
        .replace("[", "")
        .replace("]", "");

      if (tmpBreed == "개") {
        return 1;
      } else if (tmpBreed == "고양이") {
        return 2;
      }
    },
    setBreedDate() {
      this.startAt = this.tmpStartAt;
      this.endAt = this.tmpEndAt;
      this.breedDateFlag = false;

      var animal =
        this.selectedAnimal == "전체" ? "" : "[" + this.selectedAnimal + "]";
      var breedOne = this.selectedBreed == "전체" ? "" : this.selectedBreed;

      var keyword = {
        breed: animal + breedOne,
        created_at: this.startAt.replace(/\./g, "-"),
        deleted_at: this.endAt.replace(/\./g, "-"),
        address:
          this.selectedCity == "전체" && this.selectedTown == "전체"
            ? ""
            : this.selectedCity + " " + this.selectedTown,
        center_name: this.centerName == "지역" ? "" : this.centerName
      };

      this.getStrayAnimalList(keyword);
    },
    goDetail(id, breed) {
      window.sessionStorage.setItem("strayAnimalId", id);
      window.sessionStorage.setItem("detailBreed", breed.split(" ")[0]);

      this.$router.push({
        name: "strayanimaldetail"
      });
    },
    goBack() {
      this.$router.go(-1);
    },
    getStrayAnimalList(keyword) {
      this.getAxios
        .post("/breed/strayanimal/list", keyword)
        .then(res => {
          this.strayAnimalList = res.data.strayAnimalList;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    init() {
      this.addressFlag = false;
      this.centerName = "지역";
      this.breedFlag = false;
      this.breed = "품종";
      this.breedDateFlag = false;
      this.selectedCity = "전체";
      this.selectedTown = "전체";
      this.selectedCenterName = "전체";
      this.selectedAnimal = "전체";
      this.selectedBreed = "전체";
      this.makeDate();

      var keyword = {
        breed: this.breed == "품종" ? "" : this.breed,
        created_at: this.startAt.replace(/\./g, "-"),
        deleted_at: this.endAt.replace(/\./g, "-"),
        address:
          this.selectedCity == "전체" && this.selectedTown == "전체"
            ? ""
            : this.selectedCity + " " + this.selectedTown,
        center_name: this.centerName == "지역" ? "" : this.centerName
      };

      this.getStrayAnimalList(keyword);
    }
  },
  mounted() {
    this.init();
    this.tabs[0].content = this.startAt;
    this.tabs[1].content = this.endAt;
    this.tmpStartAt = this.startAt;
    this.tmpEndAt = this.endAt;
  }
};
</script>
<style>
.v-menu__content.theme--light.menuable__content__active {
  /* width: 425px !important; */
}

.v-card {
  width: 100%;
}

.card::-webkit-scrollbar {
  display: none;
}

.card {
  -ms-overflow-style: none;
}

.mx-datepicker-main.mx-datepicker-popup {
  margin-left: auto !important;
  margin-right: auto !important;
}

.dialog {
  margin: 0 !important;
}

div.v-dialog {
  margin: 0px !important;
  position: absolute !important;
  top: 156px !important;
}
</style>