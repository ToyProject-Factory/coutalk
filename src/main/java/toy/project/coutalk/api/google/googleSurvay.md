# 구글 스크립트 API 정보 
[설문지 링크](https://forms.gle/tR47Pa1TGXFYT3baA)
    
    function submit(e) {
    // 응답을 results 배열에 넣음
    let kakaoId = "", kakaoKeyword = "", isRank = 0, isHotdeal = 0, isPersonalSecurity =  0

    const items = e.response.getItemResponses()
    console.log("items = " + items)

    items.map((item) => {
        if(item.getItem().getTitle() == '카카오톡 ID'){
            kakaoId = item.getResponse()
        }
        else if (item.getItem().getTitle().toString().includes('키워드') ){
            kakaoKeyword = item.getResponse()
        }
        else if (item.getItem().getTitle() == '추가 옵션'){
            if(item.getResponse().toString().includes('랭킹')){
                isRank = 1
            }
            if(item.getResponse().toString().includes('핫딜')){
                isHotdeal = 1
            }
        }
        else if (item.getItem().getTitle().toString().includes('개인정보')){
            if(item.getResponse().includes("동의")){
                isPersonalSecurity = 1  
            }
        }
    })
    
    const KakaoUser = {
        kakaoId: kakaoId,
        isRank: isRank,
        isHotdeal: isHotdeal,
        isPersonalSecurity: isPersonalSecurity,
        kakaoKeyword : [{
            kakaoId : kakaoId,
            keyword : kakaoKeyword
        }]
    }
    
    UrlFetchApp.fetch(`https://5940-190-195-120-73.ngrok-free.app/coutalk/api/googleSurvey`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        payload:  JSON.stringify(KakaoUser)
        })
    }
