<template>
  <v-container
    fluid
    grid-list-xl
    style="max-width: 425px !important; margin-top: 28vh;"
    class="mx-auto"
  >
    <v-layout row wrap fill-height>
      <v-flex class="bg"></v-flex>
      <v-flex class="bg layer-2"></v-flex>
      <v-flex class="bg layer-3"></v-flex>
      <v-flex stlye="z-index: 50000;">
        <v-flex xs12>
          <v-flex class="speech-bubble">
            <v-flex
              text-center
              class="ma-0 pa-0"
              v-html="content"
              style="width: 100%; font-size: 13px; overflow: hidden;"
            ></v-flex>
          </v-flex>
          <v-flex class="dog text-center">
            <img
              :src="require(`@/assets/${img}`)"
              style="width: 40%; height: 20vh; cursor: pointer;"
              @click="changeContent()"
            />
          </v-flex>
        </v-flex>
      </v-flex>
      <v-dialog v-model="dialog" max-height="100px" max-width="325px" style="z-index: 1000000;">
        <v-flex>
          <v-flex class="pa-2" style="background-color: #ffffff">오늘의 퀴즈</v-flex>
        </v-flex>
        <v-flex>
          <v-card
            tile
            outlined
            v-for="(quiz, quizIdx) in todayQuizList"
            :key="quizIdx"
            class="my-0"
          >
            <v-divider></v-divider>
            <v-flex class="pa-3 pb-0">
              <v-flex style="font-size: 12px">{{ quizIdx + 1 }}. {{ quiz.contents }}</v-flex>
              <v-flex d-flex class="pl-3 pb-0 my-auto" style="height: 100%">
                <v-radio-group :mandatory="false" column>
                  <v-flex>
                    <v-flex
                      v-for="(quizChoice, choiceIdx) in quiz.quizChoiceList"
                      :key="choiceIdx"
                      style="color: #84898c; font-size: 12px;"
                    >
                      <v-flex class="pl-2 pb-1 my-auto">
                        <v-radio
                          style="font-size: 1px"
                          :label="'(' + (choiceIdx + 1) + ') ' + quizChoice"
                          :value="'quiz' + quiz.id + 'choice' + (choiceIdx + 1)"
                          @change="selectNumber(quiz.id, (choiceIdx + 1))"
                        ></v-radio>
                      </v-flex>
                    </v-flex>
                  </v-flex>
                </v-radio-group>
              </v-flex>
            </v-flex>
          </v-card>
        </v-flex>
        <v-flex style="background-color: #ffffff" class="text-right pa-3">
          <v-btn @click.prevent="solveQuiz()" color="#658dc6" dark tile depressed>제출</v-btn>
        </v-flex>
      </v-dialog>
      <v-dialog
        v-model="resultDialog"
        persistent
        max-height="100px"
        max-width="325px"
        style="z-index: 1000000;"
      >
        <v-flex>
          <v-card tile style="border-radius: 0px;">
            <v-card-title class="pt-0 pb-0">
              <v-flex
                d-flex
                class="mt-10 pt-0 justify-center"
                style="border-radius: 0px !important; font-size: 15px; font-weight: bold;"
                v-if="answerCnt == 0 && quizResultList.length == 2"
              >둘 다 틀렸습니다.</v-flex>
              <v-flex
                d-flex
                class="mt-10 pt-0 justify-center"
                style="border-radius: 0px !important; font-size: 15px; font-weight: bold;"
                v-else-if="answerCnt == 1 && quizResultList.length == 2"
              >{{ answerQuiz }}번을 틀렸습니다.</v-flex>
              <v-flex
                d-flex
                class="mt-10 pt-0 justify-center"
                style="border-radius: 0px !important; font-size: 15px; font-weight: bold;"
                v-else-if="answerCnt == 0 && quizResultList.length == 1"
              >{{ answerQuiz }}번을 틀렸습니다.</v-flex>
              <v-flex
                d-flex
                class="mt-10 pt-0 justify-center"
                style="border-radius: 0px !important; font-size: 15px; font-weight: bold;"
                v-else-if="answerCnt == 2 && quizResultList.length == 2"
              >둘 다 정답입니다.</v-flex>
              <v-flex
                d-flex
                class="mt-10 pt-0 justify-center"
                style="border-radius: 0px !important; font-size: 15px; font-weight: bold;"
                v-else-if="answerCnt == 1 && quizResultList.length == 1"
              >정답입니다.</v-flex>
            </v-card-title>
            <v-card-actions class="pa-0">
              <v-flex d-flex class="mt-11 mb-10 justify-center">
                <v-btn
                  min-width="100px"
                  height="45px"
                  color="#658dc6"
                  dark
                  tile
                  depressed
                  @click="close()"
                >확인</v-btn>
              </v-flex>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "MainPage",
  data() {
    return {
      email: this.$store.getters.getUser.email,
      userSelectedList: new Map(),
      dialog: false,
      content: "",
      todayQuizList: [],
      btnList: [],
      resultDialog: false,
      quizResultList: [],
      answerCnt: 0,
      answerQuiz: "",
      img: "",
      fk_animal_id: this.$store.getters.getUser.fk_animal_id,
      contents: []
      // contents: [
      //   "로그인하고 이용해 보세요!",
      //   "<span style='color: #658DC6'>집사</span>가 될<br>준비가 되었나요?",
      //   "반려 동물이 아플 땐<br><span style='color: #658DC6'>챗봇</span>에게 질문해보세요.",
      //   "반려 동물의 사진을 올려보세요!"
      // ]
    };
  },
  computed: {
    ...mapGetters(["getAxios", "getUser"]),
    getAxios: function() {
      return this.$store.getters.getAxios;
    }
    // contents: function() {
    //   // return "display: none;";

    //   if (this.$store.getters.isLoggedIn) {
    //     return "display: none;";
    //   } else {
    //     return [
    //       "로그인 시 대표 펫을 설정할 수 있습니다.",
    //       "내 반려동물 캐릭터에게 맞춤 정보를 받고 싶으세요?"
    //     ];
    //   }
    // }
  },
  watch: {},
  methods: {
    getContent() {
      var idx = Math.floor(Math.random() * this.contents.length);
      return this.contents[idx].script;
    },
    changeContent() {
      var tmp = this.content;
      this.content = this.getContent();

      if (tmp == this.content) {
        this.content = this.getContent();
      }
    },
    selectNumber(quizId, selectNum) {
      this.userSelectedList.set(quizId, selectNum);
    },
    solveQuiz() {
      this.answerCnt = 0;
      this.answerQuiz = "";

      if (this.userSelectedList.size != this.todayQuizList.length) {
        alert("답을 선택해주세요");
      } else {
        var quizList = [];

        this.userSelectedList.forEach(function(element, index) {
          var quiz = {
            id: index,
            answer: element
          };
          quizList.push(quiz);
        });

        this.getAxios
          .post("/study/quiz/solve/" + this.email, quizList)
          .then(res => {
            this.dialog = false;
            this.resultDialog = true;
            this.quizResultList = res.data.quizResultList;
            console.log(res);

            for (let i = 0; i < this.quizResultList.length; i++) {
              if (this.quizResultList[i].is_answer == 1) {
                this.answerCnt += 1;
              } else {
                this.answerQuiz = i + 1;
              }
            }
            console.log(this.answerCnt);
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    close() {
      this.resultDialog = false;
    },
    getScript() {}
  },
  // beforeRouteEnter(to, from, next) {
  //   next(vm => {
  //     if (vm.$store.getters.isLoggedIn) {
  //       next({ path: "/main" });
  //     } else {
  //       next({ path: "/main" });
  //     }
  //   });
  // },
  mounted() {
    // this.email = "pjj2056@naver.com";

    this.getAxios
      .get("/study/quiz/today/" + this.email)
      .then(res => {
        this.todayQuizList = res.data.todayQuizList;

        if (this.todayQuizList.length > 0) {
          this.dialog = true;
        }
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });

    if (this.fk_animal_id == 1) {
      this.img = "dog1.gif";
    } else if (this.fk_animal_id == 2) {
      this.img = "cat.gif";
    } else if (this.fk_animal_id == 3) {
      this.img = "maltese.gif";
    } else if (this.fk_animal_id == 4) {
      this.img = "golden.gif";
    } else if (this.fk_animal_id == 5) {
      this.img = "russianBlue.gif";
    } else if (this.fk_animal_id == 6) {
      this.img = "persian.gif";
    }

    this.getAxios
      .get("/home/script/" + this.email)
      .then(res => {
        this.contents = [];
        // this.contents = res.data.scripts;
        this.contents = res.data.scripts;
        this.content = this.contents[0].script
        // tmp.forEach(function(element) {
        //   this.tests.push(element.script);
        // });
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>
<style scoped>
.speech-bubble {
  position: relative;
  background: #ffffff;
  border-radius: 0.8em;
}

.speech-bubble:after {
  content: "";
  position: absolute;
  bottom: 0px;
  left: 50%;
  border: 13px solid transparent;
  border-top-color: #ffffff;
  border-bottom: 0;
  margin-left: -12px;
  margin-bottom: -12px;
}

.dog {
  position: relative;
}

.bg {
  position: fixed;
  max-width: 425px;
  margin-left: auto;
  margin-right: auto;
  left: 0;
  right: 0;
  top: -50%;
  bottom: -50%;
  animation: slide 1s;
  background: linear-gradient(175deg, #0fd9ed 50%, #6825d7 50%);
}

.layer-2 {
  animation-direction: alternate-reverse;
  animation-duration: 1s;
}

.layer-3 {
  animation-duration: 1s;
}

@keyframes slide {
  0% {
    transform: translateY(-50%);
  }
}

.v-dialog.v-dialog--active {
  position: absolute !important;
  top: 12% !important;
}

.v-dialog.v-dialog--active.v-dialog--persistent {
  position: absolute !important;
  top: 30% !important;
}

.v-label.theme--light {
  font-size: 12px;
}

.v-input.theme--light.v-input--selection-controls.v-input--radio-group.v-input--radio-group--column {
  margin-top: 5px;
}
</style>