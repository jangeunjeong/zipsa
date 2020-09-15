<template> <!-- 산책하기 등 미션 시작 전에 오버레이필요한 경우 -->
  <v-container fluid grid-list-xl fill-height>
    <v-layout row wrap>
      <v-flex>

      <!-- <v-breadcrumbs :items="items">
        <template v-slot:divider>
          <v-icon>mdi-chevron-right</v-icon>
        </template>
      </v-breadcrumbs> -->

        <!-- 현재 페이지 안내 시작 -->
        <v-flex>
            <ul class="v-breadcrumbs theme--light">
                <!-- <li></li> -->
                <li class="v-breadcrumbs__divider">
                    <i aria-hidden="true" class="v-icon notranslate mdi mdi-chevron-right theme--light"></i>
                </li>
                <li>산책하기</li> <!-- <a href="breadcrumbs_link_1" class="v-breadcrumbs__item">Link 1</a> -->
            </ul>
        </v-flex>
        <!-- 현재 페이지 안내 끝-->



        <!-- 슬라이드 카드 영역 시작 -->
        <v-carousel cycle height="200" hide-delimiter-background show-arrows-on-hover>
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
                <v-sheet :color="colors[i]" height="100%">
                    <v-row class="fill-height" align="center" justify="center">
                        <div class="display-3">{{ slide }} Slide</div>
                    </v-row>
                </v-sheet>
            </v-carousel-item>
       </v-carousel>
       <!-- 슬라이드 카드 영역 끝 -->

        <!-- <v-row>
            <v-col > -->
                <v-container fluid>
                <v-row>
                    <v-col class="d-flex child-flex" >
                    <v-img src="" width="1px"  ></v-img>      
                    <v-img src="../../assets/mission7/day1_O.png" width="40px"></v-img>
                    <v-img src="../../assets/mission7/day2_O.png" width="40px"></v-img>
                    <v-img src="../../assets/mission7/day3_X.png" width="40px"></v-img>
                     <!-- 이미지 잘림 수정필요 aspect-ratio="1" /  width="40px"   -->
                      <v-img src="" width="1px"  ></v-img>
                    </v-col>
                </v-row>
                    <v-row>
                    <v-col class="d-flex child-flex" >      
                    
                    <v-img src="../../assets/mission7/day4_X.png"></v-img>
                    <v-img src="../../assets/mission7/day5_X.png"></v-img>
                    <v-img src="../../assets/mission7/day6_X.png"></v-img>
                    <v-img src="../../assets/mission7/day7_X.png"></v-img>
                   
                    </v-col>
                </v-row>

                <v-btn color="primary" dark @click="overlay = !overlay">산책시작
                    <v-icon dark right>done</v-icon>
                </v-btn>

                <v-overlay :absolute="absolute" :value="overlay" > <!-- 더 진하게 되나 --> 
                  <h3>목줄 : 3만원</h3>
                  <h3>배변봉투 : 1만원</h3>
                  <hr>
                  <h3>총 : 4만원</h3>
                  <v-btn color="success" @click="overlay = false"  v-on:click="completeBtn">
                    확인
                  </v-btn>
                </v-overlay>


                </v-container>
            <!-- </v-col>
        </v-row> -->

        



      
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "MissionDetailPage2",
  data() {
    return {
      items: [],
       colors: [
        'indigo',
        'warning',
        'pink darken-2',
        'red lighten-1',
        'deep-purple accent-4',
      ],
      slides: [
        'First',
        'Second',
        'Third',
        'Fourth',
        'Fifth',
      ],
      absolute: true,
      overlay: false,
    };
  },
  computed: {
    getAxios: function() {
      return this.$store.getters.getAxios;
    }
  },
  methods: {
    completeBtn() {
      alert("코인 빠져나감");
      this.$router.push("/missiondetailpage3"); //선택하면 넘겨야 할까 버튼이 있어야 할까? 그리고 click아닌것 같은데
    }
  }
};
</script>
<style scoped>

</style>