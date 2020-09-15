<template>
  <v-container fluid grid-list-xl>
    <v-layout row wrap fill-height>
      <v-flex xs12>
        <v-flex d-flex class="text-center py-2" style="background-color: #ffffff">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>{{ title }}</v-flex>
        </v-flex>
        <v-divider></v-divider>
      </v-flex>
      <v-flex>
        <!-- 슬라이드 이미지 영역 시작 -->
        <v-carousel
          delimiter-icon="stop"
          prev-icon="mdi-arrow-left"
          next-icon="mdi-arrow-right"
          :interval="2500"
          cycle
          height="200"
          :continuous="true"
          :show-arrows="false"
          hide-delimiter-background
        >
          <v-carousel-item v-for="(item,i) in mission_images" :src="item" :key="i" class="mx-auto"></v-carousel-item>
          <!--  width="100%" -->
        </v-carousel>
        <!-- 슬라이드 이미지 영역 끝-->

        <!-- <v-row>
        <v-col >-->
        <v-container fluid grid-list-xl>
          <v-flex v-if="mission_id=='9' || mission_id=='8'">
            <!-- 주1회 미션 영역 시작-->
            <v-flex align-self-center class="mb-12 text-center">
              <v-flex class="mb-3 text-center mx-auto">
                <div v-if="status==false">
                  <v-img src="../../assets/mission7/day1_O.png" width="30vh" class="mx-auto"></v-img>
                </div>
                <div v-if="status==true">
                  <v-img src="../../assets/mission7/day1_X.png" width="30vh" class="mx-auto"></v-img>
                </div>
                <v-flex class="text-center mx-auto justify-center">
                  <v-btn
                    color="primary"
                    dark
                    :disabled="dialog"
                    :loading="dialog"
                    @click="btnDialog = true; dialog = true"
                  >
                    미션하기
                    <v-icon dark right>done</v-icon>
                  </v-btn>
                </v-flex>
              </v-flex>
            </v-flex>
          </v-flex>
          <v-flex v-else>
            <v-row>
              <v-col class="d-flex child-flex">
                <v-img src width="1px"></v-img>
                <v-img v-if="day1==false" src="../../assets/mission7/day1_X.png" width="40px"></v-img>
                <v-img v-if="day1==true" src="../../assets/mission7/day1_O.png" width="40px"></v-img>
                <v-img v-if="day2==false" src="../../assets/mission7/day2_X.png" width="40px"></v-img>
                <v-img v-if="day2==true" src="../../assets/mission7/day2_O.png" width="40px"></v-img>
                <v-img v-if="day3==false" src="../../assets/mission7/day3_X.png" width="40px"></v-img>
                <v-img v-if="day3==true" src="../../assets/mission7/day3_O.png" width="40px"></v-img>
                <!-- 이미지 잘림 수정필요 aspect-ratio="1" /  width="40px"   -->
                <v-img src width="1px"></v-img>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="d-flex child-flex">
                <v-img v-if="day4==false" src="../../assets/mission7/day4_X.png"></v-img>
                <v-img v-if="day4==true" src="../../assets/mission7/day4_O.png"></v-img>
                <v-img v-if="day5==false" src="../../assets/mission7/day5_X.png"></v-img>
                <v-img v-if="day5==true" src="../../assets/mission7/day5_O.png"></v-img>
                <v-img v-if="day6==false" src="../../assets/mission7/day6_X.png"></v-img>
                <v-img v-if="day6==true" src="../../assets/mission7/day6_O.png"></v-img>
                <v-img v-if="day7==false" src="../../assets/mission7/day7_X.png"></v-img>
                <v-img v-if="day7==true" src="../../assets/mission7/day7_O.png"></v-img>
              </v-col>
            </v-row>
            <v-flex class="text-center">
              <v-btn
                color="primary"
                dark
                :disabled="dialog"
                :loading="dialog"
                @click="btnDialog = true; dialog = true"
              >
                미션하기
                <v-icon dark right>done</v-icon>
              </v-btn>
            </v-flex>
          </v-flex>
        </v-container>

        <!-- dialog 시작 -->
        <v-dialog v-model="btnDialog" persistent max-width="290">
          <v-card>
            <v-card-title class="headline">필요해요</v-card-title>
            <v-flex v-for="(item, index) in items" :key="index">
              <v-card-text>{{ item.item_name }} : {{ item.item_price }}원</v-card-text>
            </v-flex>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text v-on:click="completeBtn">미션완료</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- dialog 끝 -->
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "MissionDetailPage",
  data() {
    return {
      email: this.$store.getters.getUser.email,
      nickname: this.$store.getters.getUser.nickname,
      fk_animal_id: this.$store.getters.geFk_animal_id,
      mission_id: this.$route.params.mission_id,
      mission_state_id: null,
      // mission_slides : this.$route.params.mission_slides,
      // mission_colors : this.$route.params.mission_colors,
      mission_images: this.$route.params.mission_images,
      title: window.sessionStorage.getItem("title"),
      day1: false,
      day2: false,
      day3: false,
      day4: false,
      day5: false,
      day6: false,
      day7: false,
      status: false,
      btnDialog: false,
      dialog: false,
      items: []
    };
  },
  computed: {
    ...mapGetters(["requestHeader", "getUser", "getAxios"])
  },
  methods: {
    goBack() {
      this.$router.push("/missionlistpage");
    },
    completeBtn() {
      this.btnDialog = false;
      this.fn_mission_complete();
      // this.fn_mission_final_check();
    },
    fn_mission_state() {
      this.getAxios
        .get("/mission/" + this.mission_id + "/" + this.email)
        .then(res => {
          if (res.data.mission_state._daily == true) {
            (this.day1 = res.data.mission_state.day1),
              (this.day2 = res.data.mission_state.day2),
              (this.day3 = res.data.mission_state.day3),
              (this.day4 = res.data.mission_state.day4),
              (this.day5 = res.data.mission_state.day5),
              (this.day6 = res.data.mission_state.day6),
              (this.day7 = res.data.mission_state.day7);
          } else {
            //res.data.mission_state._daily == false
            this.status = res.data.status;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    fn_mission_item() {
      this.getAxios
        .get("/mission/items/" + this.mission_id)
        .then(res => {
          this.items = res.data.items;
        })
        .catch(err => {
          console.log(err);
        });
    },
    fn_mission_complete() {
      this.getAxios
        .get(
          "/mission/set?mission_id=" + this.mission_id + "&email=" + this.email
        )
        .then(res => {
          if (res.data.result == "TRUE") {
            //  alert("TRUE");
            this.fn_mission_final_check();
          } else {
            alert("문제가 생겼어요~ 다시 시도해 주세요~");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    fn_mission_final_check() {
      this.getAxios
        .get("/mission/check/final/" + this.email)
        .then(res => {
          if (res.data.result == "TRUE") {
            alert("모든 미션이 끝났습니다!");
            this.$router.push({
              name: "CertificationPage",
              params: {
                id: this.fk_animal_id,
                certi_have: true
              }
            });
          } else {
            //"FALSE"
            this.$router.push("/missionlistpage");
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  mounted() {
    // console.log("email : " + this.email);
    // console.log("nickname : " + this.nickname);
    // console.log("fk_animal_id : " + this.fk_animal_id);
    // console.log("mission_id : " + this.mission_id);
    // console.log("mission_images : " + this.mission_images);

    this.fn_mission_state();
    this.fn_mission_item();
  }
};
</script>
<style scoped>
</style>