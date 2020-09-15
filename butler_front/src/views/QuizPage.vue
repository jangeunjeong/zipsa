<template>
  <v-container
    fluid
    style="max-width: 425px !important; background-color: #ffffff;"
    class="mx-auto pt-0 pb-0"
  >
    <v-layout row wrap fill-height>
      <v-flex xs12>
        <v-flex d-flex class="text-center py-2" style="background-color: #ffffff">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>퀴즈 보기</v-flex>
        </v-flex>
        <v-divider></v-divider>
      </v-flex>
      <v-tabs background-color="#ffffff" color="#658dc6" height="38px">
        <v-tab class="font-weight-bold" style="width: 100%;">오늘의 퀴즈</v-tab>
        <v-tab class="font-weight-bold" style="width: 100%;">내가 푼 퀴즈</v-tab>
        <v-tab-item>
          <v-layout row wrap fill-height>
            <v-flex v-if="todayQuizFlag">
              <v-card
                tile
                outlined
                v-for="(quiz, quizIdx) in todayQuizList"
                :key="quizIdx"
                class="mb-0"
              >
                <v-flex class="pa-3 pb-0">
                  <v-flex>{{ quizIdx + 1 }}. {{ quiz.contents }}</v-flex>
                  <v-flex d-flex class="pl-3 pb-0">
                    <v-radio-group :mandatory="false" column>
                      <v-flex>
                        <v-flex
                          v-for="(quizChoice, choiceIdx) in quiz.quizChoiceList"
                          :key="choiceIdx"
                          style="color: #84898c; font-size: 12px;"
                        >
                          <v-flex class="pl-2 pb-1">
                            <v-radio
                              :label="'(' + (choiceIdx + 1) + ') ' + quizChoice"
                              @change="selectNumber(quiz.id, (choiceIdx + 1))"
                              :v-model="'quiz' + quiz.id + 'choice' + (choiceIdx + 1)"
                            ></v-radio>
                          </v-flex>
                        </v-flex>
                      </v-flex>
                    </v-radio-group>
                  </v-flex>
                </v-flex>
              </v-card>
              <v-flex style="background-color: #ffffff" class="text-right py-2">
                <v-btn @click.prevent="solveQuiz()" color="#658dc6" dark tile depressed>제출</v-btn>
              </v-flex>
            </v-flex>
            <v-flex
              v-else
              style="background-color: #ffffff; height: 100%"
              class="mt-12 pt-12 text-center"
            >
              <v-flex class="mt-12 pt-12">
                <v-flex class="mt-12 pt-5">오늘의 퀴즈를 모두 풀었습니다.</v-flex>
              </v-flex>
            </v-flex>
          </v-layout>
        </v-tab-item>
        <v-tab-item>
          <v-layout row wrap fill-height>
            <v-flex v-if="solvedQuizFlag">
              <v-card
                tile
                outlined
                v-for="(quiz, quizIdx) in solvedQuizList"
                :key="quizIdx"
                class="mb-0"
              >
                <v-flex class="pa-3 pb-0">
                  <v-flex>{{ quizIdx + 1 }}. {{ quiz.contents }}</v-flex>
                  <v-flex d-flex class="pl-3 pb-0">
                    <v-radio-group :mandatory="false" column>
                      <v-flex>
                        <v-flex
                          v-for="(quizChoice, choiceIdx) in quiz.quizChoiceList"
                          :key="choiceIdx"
                          style="color: #84898c; font-size: 12px;"
                        >
                          <v-flex class="pl-2 pb-1">
                            <v-radio
                              :label="'(' + (choiceIdx + 1) + ') ' + quizChoice"
                              @change="selectNumber(quiz.id, (choiceIdx + 1))"
                              :v-model="'quiz' + quiz.id + 'choice' + (choiceIdx + 1)"
                            ></v-radio>
                          </v-flex>
                        </v-flex>
                      </v-flex>
                    </v-radio-group>
                  </v-flex>
                </v-flex>
              </v-card>
            </v-flex>
            <v-flex
              v-else
              style="background-color: #ffffff; height: 100%"
              class="mt-12 pt-12 text-center"
            >
              <v-flex class="mt-12 pt-12">
                <v-flex class="mt-12 pt-5">내가 푼 퀴즈가 없습니다.</v-flex>
              </v-flex>
            </v-flex>
          </v-layout>
        </v-tab-item>
      </v-tabs>
    </v-layout>
  </v-container>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  name: "QuizPage",
  computed: {
    ...mapGetters(["getUser", "getAxios"])
  },
  data() {
    return {
      email: this.$store.getters.getUser.email,
      userSelectedList: new Map(),
      selected1: "",
      selected2: "",
      selected3: "",
      todayQuizList: [],
      solvedQuizList: [],
      todayQuizFlag: false,
      solvedQuizFlag: false
    };
  },
  watch: {},
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    selectNumber(quizId, selectNum) {
      this.userSelectedList.set(quizId, selectNum);
    },
    solveQuiz() {
      if (this.userSelectedList.size != this.reQuizList.length) {
        alert("답을 선택해주세요");
      } else {
        console.log(this.userSelectedList);
      }
    },
    getTodayQuizList() {
      this.getAxios
        .get("/study/quiz/today/" + this.email)
        .then(res => {
          console.log(res);
          // this.todayQuizList = res.data.todayQuizList;
          this.todayQuizList = res.data.todayQuizList;

          if (this.todayQuizList.length > 0) {
            this.todayQuizFlag = true;
          }
          // console.log(this.todayQuizList.length);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getMyQuizList() {
      this.getAxios
        .get("/study/solvedquiz/" + this.email)
        .then(res => {
          this.solvedQuizList = res.data.solvedQuizList;

          if (this.solvedQuizList.length > 0) {
            this.solvedQuizFlag = true;
          }
          console.log(res);
        })
        .then(err => {
          console.log(err);
        });
    }
  },
  mounted() {
    this.getTodayQuizList();
    this.getMyQuizList();
  }
};
</script>