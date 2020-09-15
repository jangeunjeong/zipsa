<template>
    <v-card class="overflow-hidden" flat>
        <v-app-bar
            absolute="absolute"
            color="#b5c7d3"
            height= "78"
            elevate-on-scroll="elevate-on-scroll"
            scroll-target="#scrolling-techniques-7"
            centerTitle:="centerTitle:"
            true="true">
            <v-spacer></v-spacer>
            <v-img src="../assets/behavior_chat.png" max-width="30px"/>
            <v-toolbar-title class="pl-2">행동정보 챗봇</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn to="/myhome" icon="icon" class="pr-4">
                <v-icon>mdi-account-circle</v-icon>
            </v-btn>
        </v-app-bar>
        <v-sheet
            id="scrolling-techniques-7"
            class="overflow-y-auto"
            max-height="600"
            color="white">
            <v-container fluid="fluid" grid-list-xl="grid-list-xl">
                <v-layout row="row" wrap="wrap">
                    <v-flex x12="x12" class="mt-12 pt-10">
                        <v-card
                            style="height: 420px"
                            class="overflow-y-auto"
                            ref="botDialog"
                            outlined=""
                            color="rgb(230, 230, 230)"
                            tile="">
                            <v-container v-for="(message, idx) in chatMessages" :key="idx">
                                <div v-if="message.speaker === 'user'" class="d-flex flex-column align-end">
                                    <v-card shaped min-width="50px" max-width="250px" color="#658dc6" class="white--text">
                                        <p class="mb-0 py-2 pl-2 pr-2 text-center">{{ message.msg }}</p>
                                    </v-card>
                                </div>
                                <div v-else class="d-flex flex-column">
                                    <v-img src="../assets/zipsa_chat.png" width="40px" height="40px" class="mb-1"/>
                                    <v-card shaped min-width="50px" max-width="250px" color="white" class="white--black">
                                        <p class="mb-0 pa-2">{{ message.msg }}</p>
                                        <div v-if="tmpIdx > 0">
                                        <div v-for="(total, idx1) in totalList" :key="idx1" class="mb-0 pa-2">
                                          <div v-if="(idx / 2) - 1 == total.idx">
                                          <v-flex v-if="total.txtList.length > 0">
                                          <p v-for="(txt, txtIdx) in total.txtList" :key="txtIdx">{{ txt }}</p>
                                          </v-flex>
                                          <v-flex v-if="total.imgList[0]">
                                          <img :src="total.imgList[0]" width="220">
                                          </v-flex>
                                          </div>
                                        </div>
                                        </div>
                                        <!-- <div v-for="(iimg, idx2) in imgList" :key="idx2" class="mb-0 pa-2">
                                          <img :src="iimg" width="220">
                                        </div> -->
                                    </v-card>
                                </div>
                            </v-container>
                        </v-card>
                        <v-card outlined="" tile="">
                            <form @submit.prevent="submitMessage">
                                <div class="pl-4 d-flex justify-end">
                                  <div v-for="(chatButton, idx) in btnList" :key="idx">
                                                <v-btn rounded 
                                                    @click="chatClickButton(chatButton, chatButton, idx)"
                                                    class="white--text"
                                                    color="pink">
                                                    {{ chatButton }}
                                                </v-btn>
                                  </div>
                                    <!-- <v-text-field color="#F5B895" v-model="textInput" placeholder="질문사항을 작성해주세요"></v-text-field> -->
                                    <!-- <v-btn type="submit" icon="icon" class="mt-3 ml-2 pr-4">
                                        <v-icon color="pink">mdi-arrow-up-bold-circle</v-icon>
                                    </v-btn> -->
                                </div>
                            </form>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-sheet>
    </v-card>
</template>
​

<script>
import {
  mdiPencilBoxOutline,
  mdiRobot,
  mdiOpenInNew,
  mdiAccount,
  mdiBellOutline,
  mdiAccountQuestionOutline
} from '@mdi/js'
  export default {
    name: 'NurtureChatPage',
    data() {
      return {
        chatDialog: false,
        textInput: '',
        moment: require('moment'),
        chatMessages: [{
          speaker: 'bot',
          msg: "저는 행동지킴이랍니다! 궁금한 것이 있다면 저에게 물어보세요! ๑•‿•๑",}],
        icons: {
          mdiPencilBoxOutline,
          mdiRobot,
          mdiOpenInNew,
          mdiAccount,
          mdiBellOutline,
          mdiAccountQuestionOutline
        },
        tmpIdx : -1,
        btnList: ['강아지','고양이'],
        // txtList: [],
        // imgList: [],
        totalList: [],
        tmpImg: '',
      }
    },
    watch: {
      chatMessages: function() {
        this.$nextTick(function() {
          this.$refs.botDialog.$el.scrollTop = this.$refs.botDialog.$el.scrollHeight
        })
      }
    },
    computed: {
      getAxios: function() {
        return this.$store.getters.getAxios
      },
      requestHeader: function() {
        return this.$store.getters.requestHeader
      },
      logoChange: function() {
        if (this.$vuetify.breakpoint.smAndUp) {
          return "width: 75px; height: 50px;"
        }
        else {
          return "width: 45px; height: 30px;"
        }
      }
    },
    methods: {
      submitMessage() {
        if (this.textInput === '') {
          return false
        }
        this.chatMessages.push({speaker: 'user', msg: this.textInput})
        this.textInput = ''
      },
      chatClickButton(message, chatButton, idx) {
        this.getAxios
        .get("/user/chatbot/"+message)
        .then(res => {
          console.log(res);
          var list = [];
          var options = [];
          var txts=[];
          var imgs=[];
          var title = res.data.response.generic[0].title;

          this.chatMessages.push({speaker: 'bot', msg: title})
          console.log(this.chatMessages)

          list = res.data.response.generic[0].options;

          list.forEach(function(element, index) {
            var ty=element.label[0];
            if(ty=='^'){
              options.push(element.label.substring(1,element.label.length));
            } else if(ty=='-'){
              element.label='['+element.label.substring(1,element.label.length)+']';
              txts.push(element.label)
              txts.push(element.value.input.text)
            }else{
              imgs.push(element.label.substring(1,element.label.length))
            }
          });
          this.btnList = options;

          this.tmpIdx += 1;

          var tmp = {
            idx: this.tmpIdx,
            txtList: txts,
            imgList: imgs
          }
          this.totalList.push(tmp);

          console.log(this.totalList)
          console.log(txts);
          this.txtList = txts;
          console.log(this.txtList)
          this.imgList = imgs;

          // options.forEach(function(element, index) {
          //   // alert(element)
          //  this.chatMessages.push({speaker: 'bot', msg: element})
          // });
          // txts.forEach(function(element, index) {
          //   // alert(element)
          //  this.chatMessages.push({speaker: 'bot', msg: element})
          // });
          // imgs.forEach(function(element, index) {
          //   // alert(element)
          //  this.chatMessages.push({speaker: 'bot', msg: element})
          // });
        })
        .catch(err => {
          console.log(err);
        });

          this.textInput = chatButton
          this.submitMessage()
      },
    }
  }
</script>