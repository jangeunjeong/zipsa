<template> <!-- 산책하기 등 미션 시작 전에 오버레이필요한 경우 -->
  <v-container fluid grid-list-xl fill-height>
    <v-layout row wrap>
      <v-flex>

 

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


    



      
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "MissionDetailPage3",
  data() {
    return {
      items: [],
    };
  },
  computed: {
    getAxios: function() {
      return this.$store.getters.getAxios;
    }
  },
  methods: {
    completeBtn() {
      //alert("코인 빠져나감");
     // this.$router.push("/main"); //선택하면 넘겨야 할까 버튼이 있어야 할까? 그리고 click아닌것 같은데
    }
  }
};
</script>
<style scoped>

</style>