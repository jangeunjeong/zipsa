<template>
  <v-container fluid grid-list-xl fill-height>
    <v-layout row wrap>
      <v-flex>
        <!-- <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>mdi-chevron-right</v-icon>
        </template>
        </v-breadcrumbs>-->

        <!-- 현재 페이지 안내 시작 -->
        <!-- <v-flex>
            <ul class="v-breadcrumbs theme--light">
                <li class="v-breadcrumbs__divider">
                    <i aria-hidden="true" class="v-icon notranslate mdi mdi-chevron-right theme--light"></i>
                </li>
        <li>{{ title }}</li> -->
        <!-- <a href="breadcrumbs_link_1" class="v-breadcrumbs__item">Link 1</a> -->
        <!-- </ul>
        </v-flex> -->
        <!-- 현재 페이지 안내 끝-->

        <!-- 슬라이드 카드 영역 시작 -->
        <!-- <v-carousel cycle height="200" :interval="2500" hide-delimiter-background show-arrows-on-hover>
            <v-carousel-item v-for="(slide, i) in mission_slides" :key="i">
                <v-sheet :color="mission_colors[i]" height="100%">
                    <v-row class="fill-height" align="center" justify="center">

                      <v-container fill-height>
                        <v-layout align-center class="mx-auto">
                          <v-flex>
                            <h6 class="subtitle-2 text-center">{{slide}}</h6>
                          </v-flex>
                        </v-layout>
                      </v-container>

                    </v-row>
                </v-sheet>
            </v-carousel-item>
        </v-carousel>-->
        <!-- 슬라이드 카드 영역 끝 -->

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
        <v-container fluid>
          <v-flex v-if="mission_id=='9' || mission_id=='8'">
            <!-- 주1회 미션 영역 시작--> 
            <v-flex align-self-center class="mb-12">
            <v-flex class="mb-3">
              <div v-if="status==false">
                <v-img src="../../assets/mission7/day1_O.png" width="30vh" class="mx-auto"></v-img>
              </div>
              <div v-if="status==true">
                <v-img src="../../assets/mission7/day1_X.png" width="30vh" class="mx-auto"></v-img>
              </div>

              <v-btn color="primary" dark v-on:click="completeBtn" >
              미션완료 <v-icon dark right>done</v-icon> 
            </v-btn>

            </v-flex>

            

          </v-flex>
          <!-- 주1회 미션 영역 끝--> 
            <!-- <v-btn color="primary" dark v-on:click="completeBtn">
              미션완료 <v-icon dark right>done</v-icon> 
            </v-btn> -->
            
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
            <v-btn color="primary" dark v-on:click="completeBtn">
              미션완료 <v-icon dark right>done</v-icon>
            </v-btn>
          </v-flex>
         
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "MissionDetail_CatPage",
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
      day1 : false, 
      day2 : false,
      day3 : false,
      day4 : false,
      day5 : false,
      day6 : false,
      day7 : false,
      status : false

    };
  },
  computed: {
    // content(){
    //   var con = this.mission_slides.split('\n').join('<br/>');
    //    return con;
    //    //return this.mission_slides.split('\n');
    // },
    ...mapGetters(["requestHeader", "getUser", "getAxios"])
  },
  methods: {
    completeBtn() {
      alert("미션완료");
      // this.$router.push("/main"); //선택하면 넘겨야 할까 버튼이 있어야 할까? 그리고 click아닌것 같은데
    }
  },
  mounted() {
    console.log("email : " + this.email);
    console.log("nickname : " + this.nickname);
    console.log("fk_animal_id : " + this.fk_animal_id);
    console.log("mission_id : " + this.mission_id);
    console.log("mission_images : " + this.mission_images);
  

    this.getAxios
        .get("/mission/"+this.mission_id+"/"+this.email)
        .then(res => {
           if (res.data.mission_state._daily == true ) {
              this.day1 = res.data.mission_state.day1, 
              this.day2 = res.data.mission_state.day2,
              this.day3 = res.data.mission_state.day3,
              this.day4 = res.data.mission_state.day4,
              this.day5 = res.data.mission_state.day5,
              this.day6 = res.data.mission_state.day6,
              this.day7 = res.data.mission_state.day7
           }else{ //res.data.mission_state._daily == false
              this.status = res.data.status
           }
        
        })
        .catch(err => {
          console.log(err);
        });



  }
};
</script>
<style scoped>
</style>