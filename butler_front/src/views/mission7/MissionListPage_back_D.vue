<template>
  <v-container fluid grid-list-xl fill-height>
    <v-layout row wrap>
      <v-flex>
        <v-card>
          <v-tabs v-model="tab" background-color="transparent" fixed-tabs>
            <v-tabs-slider></v-tabs-slider>
            <v-tab href="#mobile-tabs-dog">
                <!-- Subscribe -->
              <v-img src="../../assets/mission7/dog_tab5.png" width="7vh"></v-img>
            </v-tab>
            <v-tab href="#mobile-tabs-cat">
                <!-- Contact -->
              <v-img src="../../assets/mission7/cat_tab5.png" width="7vh"></v-img>
            </v-tab>
          </v-tabs>

          <v-tabs-items v-model="tab">
            <v-tab-item :key="1" value="mobile-tabs-dog">
                <v-card flat>
                    /////////////////////////////////////////////////////
                    
                    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <!-- <v-card> -->
        
  
          <v-list two-line subheader>
            <v-subheader inset>Folders</v-subheader>
  
            <v-list-tile
              v-for="item in items"
              :key="item.title"
              avatar
              @click="item.link"
            >
              <v-list-tile-avatar>
                <v-icon :class="[item.iconClass]">{{ item.icon }}</v-icon>
              </v-list-tile-avatar>
  
              <v-list-tile-content>
                <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                <v-list-tile-sub-title>{{ item.subtitle }}</v-list-tile-sub-title>
              </v-list-tile-content>
  
              <v-list-tile-action>
                <v-btn icon ripple>
                  <v-icon color="grey lighten-1">info</v-icon>
                </v-btn>
              </v-list-tile-action>
            </v-list-tile>
  
            <v-divider inset></v-divider>
  
            <v-subheader inset>Files</v-subheader>
  
            <v-list-tile
              v-for="item in items2"
              :key="item.title"
              avatar
              @click="item.link"
            >
              <v-list-tile-avatar>
                <v-icon :class="[item.iconClass]">{{ item.icon }}</v-icon>
              </v-list-tile-avatar>
  
              <v-list-tile-content>
                <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                <v-list-tile-sub-title>{{ item.subtitle }}</v-list-tile-sub-title>
              </v-list-tile-content>
  
              <v-list-tile-action>
                <v-btn icon ripple>
                  <v-icon color="grey lighten-1">info</v-icon>
                </v-btn>
              </v-list-tile-action>
            </v-list-tile>
          </v-list>
        <!-- </v-card> -->
      </v-flex>
    </v-layout>




////////////////////////////////////////////////////////////////////////

6666666666666666666666666666666666666666666666
 
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
        <v-subheader>REPORTS</v-subheader>
        <v-list-item-group v-model="item" color="primary">
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
            :inactive="inactive"
          >
            <v-list-item-avatar v-if="avatar">
              <v-img :src="item.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title v-html="item.title"></v-list-item-title>
              <v-list-item-subtitle v-if="twoLine || threeLine" v-html="item.subtitle"></v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>



6666666666666666
                </v-card>
            </v-tab-item>
            <v-tab-item :key="2" value="mobile-tabs-cat">
                <v-card flat>
                    <v-card-text>contact22222222222222</v-card-text>
                </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "MissionListPage",
  data() {
    return {
      items: [],
      tab: "mobile-tabs-dog",
      items: [
        { icon: 'folder', iconClass: 'grey lighten-1 white--text', title: 'Photos', subtitle: 'Jan 9, 2014',link:'main'},
        { icon: 'folder', iconClass: 'grey lighten-1 white--text', title: 'Recipes', subtitle: 'Jan 17, 2014',link:'main' },
        { icon: 'folder', iconClass: 'grey lighten-1 white--text', title: 'Work', subtitle: 'Jan 28, 2014',link:'main' }
      ],
      items2: [
        { icon: 'assignment', iconClass: 'blue white--text', title: 'Vacation itinerary', subtitle: 'Jan 20, 2014',link:'main' },
        { icon: 'call_to_action', iconClass: 'amber white--text', title: 'Kitchen remodel', subtitle: 'Jan 10, 2014' ,link:'main'}
      ]
    };
  },
  computed: {
    getAxios: function() {
      return this.$store.getters.getAxios;
    }
  },
  methods: {
    nextBtn() {
      alert("next");
      this.$router.push("/finalpage"); //선택하면 넘겨야 할까 버튼이 있어야 할까? 그리고 click아닌것 같은데
    },
    yesBtn() {
      alert("yes");
      this.$router.push("/finalpage"); //선택하면 넘겨야 할까 버튼이 있어야 할까? 그리고 click아닌것 같은데
    },
    noBtn() {
      alert("no");
      this.$router.push("/finalpage"); //선택하면 넘겨야 할까 버튼이 있어야 할까? 그리고 click아닌것 같은데
    }
  }
};
</script>
<style scoped>

</style>