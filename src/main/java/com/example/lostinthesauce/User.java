package com.example.lostinthesauce;

public class User {
        private String username;

        private String password;

        private int hiscore1;

        private int hiscore2;

        private int hiscore3;

        private int hiscoreTotal;

        private int charCustom;

        /*
        public User(String username, String password, int hiscsore1, int hiscore2, int hiscore3, int hiscoreTotal, int charCustom){
            this.charCustom = charCustom;
            this.hiscore1 = hiscsore1;
            this.hiscore2 = hiscore2;
            this.hiscore3 = hiscore3;
            this.hiscoreTotal = hiscoreTotal;
            this.username=username;
            this.password=password;
        }

         */

        public User(String username, String password){
            this.username = username;
            this.password = password;
        }


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getHiscore1() {
            return hiscore1;
        }

        public void setHiscore1(int hiscore1) {
            this.hiscore1 = hiscore1;
        }

        public int getHiscore2() {
            return hiscore2;
        }

        public void setHiscore2(int hiscore2) {
            this.hiscore2 = hiscore2;
        }

        public int getHiscore3() {
            return hiscore3;
        }

        public void setHiscore3(int hiscore3) {
            this.hiscore3 = hiscore3;
        }

        public int getHiscoreTotal() {
            return hiscoreTotal;
        }

        public void setHiscoreTotal(int hiscoreTotal) {
            this.hiscoreTotal = hiscoreTotal;
        }

        public int getCharCustom() {
            return charCustom;
        }

        public void setCharCustom(int charCustom) {
            this.charCustom = charCustom;
        }

    @Override
    public String toString() {
        return username + " " + password;
    }
}
