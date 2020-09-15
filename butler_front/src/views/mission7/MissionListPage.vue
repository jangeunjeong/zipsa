<template>
  <v-container fluid grid-list-xl fill-height>
    <v-layout row wrap>
      <!-- <v-flex xs12>
        <v-flex d-flex class="text-center py-2" style="background-color: #ffffff">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>데일리 미션</v-flex>
        </v-flex>
        <v-divider></v-divider>
      </v-flex>-->
      <v-flex xs12>
        <!-- 현재 페이지 안내 -->
        <v-flex d-flex class="text-center py-2" style="background-color: #ffffff">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>데일리 미션</v-flex>
        </v-flex>
        <v-divider></v-divider>

        <v-card>
          <div v-if="fk_animal_id=='1' || fk_animal_id=='3' || fk_animal_id=='4'">
            <v-tabs v-model="tab" background-color="transparent">
              <v-tabs-slider></v-tabs-slider>
              <v-tab href="#mobile-tabs-dog">
                <!-- Subscribe -->
                <v-img src="../../assets/mission7/dog_tab5.png"></v-img>
                <!--  width="7vh" -->
              </v-tab>
            </v-tabs>
          </div>
          <div v-if="fk_animal_id=='2' || fk_animal_id=='6' || fk_animal_id=='7'">
            <v-tabs v-model="tab" background-color="transparent">
              <v-tabs-slider></v-tabs-slider>
              <v-tab href="#mobile-tabs-cat">
                <!-- Contact -->
                <v-img src="../../assets/mission7/cat_tab5.png"></v-img>
                <!--  width="7vh" -->
              </v-tab>
            </v-tabs>
          </div>
          <v-tabs-items v-model="tab">
            <v-tab-item :key="1" value="mobile-tabs-dog">
              <v-card flat class="overflow-y-auto" tile>
                <!-- 강아지 목록 시작 카드나 목록에 배경색 주고 싶은데..background-color="#0f4c81"-->
                <!-- <v-card class="mx-auto" max-width="400" tile flat> -->
                <v-list
                  :disabled="disabled"
                  :dense="dense"
                  :two-line="twoLine"
                  :three-line="threeLine"
                  :shaped="shaped"
                  :flat="flat"
                  :subheader="subheader"
                  :sub-group="subGroup"
                  :nav="nav"
                  :avatar="avatar"
                  :rounded="rounded"
                >
                  <!-- <v-subheader>REPORTS</v-subheader> -->
                  <v-list-item-group v-model="dogitem" color="primary" class="px-0">
                    <v-list-item
                      v-for="(dogitem, i) in dogitems"
                      :key="i"
                      :inactive="inactive"
                      @click="MovePage(dogitem.title, dogitem.name,dogitem.mission_id,dogitem.mission_images)"
                    >
                      <v-flex xs12 class="pa-0">
                        <v-flex d-flex>
                          <v-list-item-avatar v-if="avatar">
                            <v-img :src="dogitem.avatar"></v-img>
                          </v-list-item-avatar>
                          <v-list-item-content>
                            <!-- @click="onListTileItemClick()"  / href="main" -->
                            <v-list-item-title v-html="dogitem.title"></v-list-item-title>
                            <v-list-item-subtitle
                              v-if="twoLine || threeLine"
                              v-html="dogitem.subtitle"
                            ></v-list-item-subtitle>
                          </v-list-item-content>
                        </v-flex>
                        <v-divider></v-divider>
                        <!-- 구분선 -->
                      </v-flex>
                    </v-list-item>
                  </v-list-item-group>
                </v-list>
                <!-- </v-card> -->
                <!-- 강아지 목록 끝 -->
              </v-card>
            </v-tab-item>
            <v-tab-item :key="2" value="mobile-tabs-cat">
              <v-card flat style="height: 400px" class="overflow-y-auto" tile>
                <!-- 고양이 목록 시작 -->
                <v-list
                  :disabled="disabled"
                  :dense="dense"
                  :two-line="twoLine"
                  :three-line="threeLine"
                  :shaped="shaped"
                  :flat="flat"
                  :subheader="subheader"
                  :sub-group="subGroup"
                  :nav="nav"
                  :avatar="avatar"
                  :rounded="rounded"
                >
                  <!-- <v-subheader>REPORTS</v-subheader> -->
                  <v-list-item-group v-model="catitem" color="primary" class="px-0">
                    <v-list-item
                      v-for="(catitem, i) in catitems"
                      :key="i"
                      :inactive="inactive"
                      @click="MovePage(catitem.title, catitem.name,catitem.mission_id,catitem.mission_images)"
                    >
                      <v-flex xs12 class="pa-0">
                        <v-flex d-flex>
                          <v-list-item-avatar v-if="avatar">
                            <v-img :src="catitem.avatar"></v-img>
                          </v-list-item-avatar>
                          <v-list-item-content>
                            <v-list-item-title v-html="catitem.title"></v-list-item-title>
                            <v-list-item-subtitle
                              v-if="twoLine || threeLine"
                              v-html="catitem.subtitle"
                            ></v-list-item-subtitle>
                          </v-list-item-content>
                        </v-flex>
                        <v-divider></v-divider>
                        <!-- 구분선 -->
                      </v-flex>
                    </v-list-item>
                  </v-list-item-group>
                </v-list>
                <!-- 고양이 목록 끝 -->
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "MissionListPage",
  data() {
    return {
      fk_animal_id: this.$store.getters.geFk_animal_id,
      items: [],
      tab: "mobile-tabs-dog",
      dogitem: 9,
      dogitems: [
        {
          avatar: require("../../assets/mission7/dog_1feed.png"),
          title: "사료·물 주기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "1",
          mission_slides: [
            "아기 강아지는 생후 1개월부터\n 사료를 따뜻한 물에 불려서 먹을 수 있어요~"
          ],
          mission_colors: ["indigo"],
          mission_images: [
            require("../../assets/mission7/dog_1.feed1.png"),
            require("../../assets/mission7/dog_1.feed2.png"),
            require("../../assets/mission7/dog_1.feed3.png"),
            require("../../assets/mission7/dog_1.feed4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_2snack.png"),
          title: "간식주기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "7",
          mission_images: [
            require("../../assets/mission7/dog_7.snack1.png"),
            require("../../assets/mission7/dog_7.snack2.png"),
            require("../../assets/mission7/dog_7.snack3.png"),
            require("../../assets/mission7/dog_7.snack4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_3check.png"),
          title: "건강 확인하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "5",
          mission_images: [
            require("../../assets/mission7/dog_5.check1.png"),
            require("../../assets/mission7/dog_5.check2.png"),
            require("../../assets/mission7/dog_5.check3.png"),
            require("../../assets/mission7/dog_5.check4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_4play.png"),
          title: "놀아주기", //교감하기
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "4",
          mission_images: [
            require("../../assets/mission7/dog_4.play1.png"),
            require("../../assets/mission7/dog_4.play2.png"),
            require("../../assets/mission7/dog_4.play3.png"),
            require("../../assets/mission7/dog_4.play4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_5combing.png"),
          title: "빗질하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "6",
          mission_images: [
            require("../../assets/mission7/dog_6.combing1.png"),
            require("../../assets/mission7/dog_6.combing2.png"),
            require("../../assets/mission7/dog_6.combing3.png"),
            require("../../assets/mission7/dog_6.combing4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_6walk.png"),
          title: "산책하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "3",
          mission_images: [
            require("../../assets/mission7/dog_3.walk1.png"),
            require("../../assets/mission7/dog_3.walk2.png"),
            require("../../assets/mission7/dog_3.walk3.png"),
            require("../../assets/mission7/dog_3.walk4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_7brushteeth.png"),
          title: "양치하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "2",
          mission_images: [
            require("../../assets/mission7/dog_2.brushteeth1.png"),
            require("../../assets/mission7/dog_2.brushteeth2.png"),
            require("../../assets/mission7/dog_2.brushteeth3.png"),
            require("../../assets/mission7/dog_2.brushteeth4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_8hospital.png"),
          title: "동물병원가기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "9",
          mission_images: [
            require("../../assets/mission7/dog_9.hospital1.png"),
            require("../../assets/mission7/dog_9.hospital2.png"),
            require("../../assets/mission7/dog_9.hospital3.png"),
            require("../../assets/mission7/dog_9.hospital4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/dog_9bath.png"),
          title: "목욕시키기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "8",
          mission_images: [
            require("../../assets/mission7/dog_8.bath1.png"),
            require("../../assets/mission7/dog_8.bath2.png"),
            require("../../assets/mission7/dog_8.bath3.png"),
            require("../../assets/mission7/dog_8.bath4.png")
          ]
        }
      ],
      catitem: 10,
      catitems: [
        {
          avatar: require("../../assets/mission7/cat_1feed.png"),
          title: "사료·물 주기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "1",
          mission_images: [
            require("../../assets/mission7/cat_1.feed1.png"),
            require("../../assets/mission7/cat_1.feed2.png"),
            require("../../assets/mission7/cat_1.feed3.png"),
            require("../../assets/mission7/cat_1.feed4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_2snack.png"),
          title: "간식주기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "7",
          mission_images: [
            require("../../assets/mission7/cat_7.snack1.png"),
            require("../../assets/mission7/cat_7.snack2.png"),
            require("../../assets/mission7/cat_7.snack3.png"),
            require("../../assets/mission7/cat_7.snack4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_3check.png"),
          title: "건강 확인하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "5",
          mission_images: [
            require("../../assets/mission7/cat_5.check1.png"),
            require("../../assets/mission7/cat_5.check2.png"),
            require("../../assets/mission7/cat_5.check3.png"),
            require("../../assets/mission7/cat_5.check4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_4play.png"),
          title: "놀아주기", // 교감하기
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "4",
          mission_images: [
            require("../../assets/mission7/cat_4.play1.png"),
            require("../../assets/mission7/cat_4.play2.png"),
            require("../../assets/mission7/cat_4.play3.png"),
            require("../../assets/mission7/cat_4.play4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_5combing.png"),
          title: "빗질하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "6",
          mission_images: [
            require("../../assets/mission7/cat_6.combing1.png"),
            require("../../assets/mission7/cat_6.combing2.png"),
            require("../../assets/mission7/cat_6.combing3.png"),
            require("../../assets/mission7/cat_6.combing4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_6water.png"),
          title: "신선한 물주기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "10",
          mission_images: [
            require("../../assets/mission7/cat_10.water1.png"),
            require("../../assets/mission7/cat_10.water2.png"),
            require("../../assets/mission7/cat_10.water3.png"),
            require("../../assets/mission7/cat_10.water4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_7toilet.png"),
          title: "화장실 청소하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "11",
          mission_images: [
            require("../../assets/mission7/cat_11.toilet1.png"),
            require("../../assets/mission7/cat_11.toilet2.png"),
            require("../../assets/mission7/cat_11.toilet3.png"),
            require("../../assets/mission7/cat_11.toilet4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_8brushteeth.png"),
          title: "양치하기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "2",
          mission_images: [
            require("../../assets/mission7/cat_2.brushteeth1.png"),
            require("../../assets/mission7/cat_2.brushteeth2.png"),
            require("../../assets/mission7/cat_2.brushteeth3.png"),
            require("../../assets/mission7/cat_2.brushteeth4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_9hospital.png"),
          title: "동물병원가기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "9",
          mission_images: [
            require("../../assets/mission7/cat_9.hospital1.png"),
            require("../../assets/mission7/cat_9.hospital2.png"),
            require("../../assets/mission7/cat_9.hospital3.png"),
            require("../../assets/mission7/cat_9.hospital4.png")
          ]
        },
        {
          avatar: require("../../assets/mission7/cat_10bath.png"),
          title: "목욕시키기",
          subtitle: "",
          link: "/missiondetailpage",
          name: "missiondetailpage",
          mission_id: "8",
          mission_images: [
            require("../../assets/mission7/cat_8.bath1.png"),
            require("../../assets/mission7/cat_8.bath2.png"),
            require("../../assets/mission7/cat_8.bath3.png"),
            require("../../assets/mission7/cat_8.bath4.png")
          ]
        }
      ],
      disabled: false,
      dense: false,
      twoLine: false,
      threeLine: false,
      shaped: false,
      flat: false,
      subheader: false,
      inactive: false,
      subGroup: false,
      nav: false,
      avatar: true,
      rounded: true
    };
  },
  computed: {
    ...mapGetters(["requestHeader", "getUser", "getAxios"])
  },
  methods: {
    goBack() {
      this.$router.push("/breed");
    },
    MovePage(title, name, mission_id, mission_images) {
      window.sessionStorage.setItem("title", title);
      this.$router.push({
        name: name,
        params: {
          mission_id: mission_id,
          // mission_slides : mission_slides,
          // mission_colors : mission_colors,
          mission_images: mission_images
        }
      });
    }
  },
  mounted() {}
  // beforeRouteEnter(to, from, next) {
  //   next(vm => {
  //     if (vm.$store.getters.isLoggedIn) {
  //       next({ path: "/main" });
  //     } else {
  //       next();
  //     }
  //   });
  // }
};
</script>
<style>
.v-item-group.theme--light.v-slide-group.v-tabs-bar.v-tabs-bar--is-mobile.primary--text.transparent {
  height: 100px;
}
</style>