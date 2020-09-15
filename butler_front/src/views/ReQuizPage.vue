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
          <v-flex>퀴즈 다시 풀기</v-flex>
        </v-flex>
      </v-flex>
      <v-flex v-if="quizFlag">
        <v-card tile outlined v-for="(quiz, quizIdx) in quizList" :key="quizIdx" class="mb-0">
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
          <v-flex class="mt-12 pt-5">틀린 퀴즈가 없습니다</v-flex>
        </v-flex>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  name: "ReQuizPage",
  computed: {
    ...mapGetters(["getUser", "getAxios"])
  },
  data() {
    return {
      email: this.$store.getters.getUser.email,
      userSelectedList: new Map(),
      quizList: [],
      quizFlag: false,
    };
  },
  watch: {
    quiz1choice1(e) {
      console.log(e);
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    selectNumber(quizId, selectNum) {
      this.userSelectedList.set(quizId, selectNum);

      if (quizId == 1) {
        this.quiz1 = true;
      } else if (quizId == 2) {
        this.quiz2 = true;
      }
    },
    solveQuiz() {
      if (this.userSelectedList.size != this.quizList.length) {
        alert("답을 선택해주세요");
      } else {
        console.log(this.userSelectedList);
      }
    }
  },
  mounted() {
    this.getAxios
      .get("/study/quiz/retry/" + this.email)
      .then(res => {
        console.log(res);
        this.quizList = res.data.result;
        
        if (this.quizList.length > 0) {
          quizFlag = true
        }
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>