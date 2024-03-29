# voyage

## APIs
Voyage relies mainly from the Wiki APIs. A lot of help came from this source .  

### Wiki
Docs: https://www.mediawiki.org/wiki/API:Main_page,  
(Page content) https://www.mediawiki.org/wiki/API:Get_the_contents_of_a_page,   
(Possible ACTIONS) https://www.mediawiki.org/w/api.php?action=help,  
Mudroljub docs: https://github.com/mudroljub/wikipedia-api-docs  


- Get downloadble image link:  
Wiki responses do not return directly the image. Instead, they may return a link to it. The link may also be found through this process:
"https://upload.wikimedia.org/wikipedia/commons/$a/$a$b/$image_name  
  - $image_name: The article's image name, with white spaces replaced by '_' such as 'Beograd_collage.jpg'.  
  - $a and $b: The first and second characters of the md5 hash generated by the image name. https://www.md5hashgenerator.com  
  - Base URL might also contain '/thumb/' after '/commons/'.

- Fetching first paragraph from article:  
https://en.wikipedia.org/w/api.php?action=query&titles=Belgrade&prop=extracts&format=json&exintro=1  

```json
{
   "batchcomplete":"",
   "warnings":{
      "extracts":{
         "*":"HTML may be malformed and/or unbalanced and may omit inline images. Use at your own risk. Known problems are listed at https://www.mediawiki.org/wiki/Special:MyLanguage/Extension:TextExtracts#Caveats."
      }
   },
   "query":{
      "pages":{
         "55904":{
            "pageid":55904,
            "ns":0,
            "title":"Belgrade",
            "extract":"<p class=\"mw-empty-elt\">\n</p>\n<p><b>Belgrade</b> (<span></span> <i title=\"English pronunciation respelling\"><span>BEL</span>-grayd</i>; Serbian Cyrillic: <span lang=\"sr-Cyrl\">\u0411\u0435\u043e\u0433\u0440\u0430\u0434</span>, <small>romanized:\u00a0</small><i lang=\"sr-Cyrl-Latn\" title=\"Serbian-language romanization\">Beograd</i>, <small>lit.\u00a0</small>'White City', <small>pronounced\u00a0</small><span title=\"Representation in the International Phonetic Alphabet (IPA)\">[be\u01d2\u0261rad]</span> <span>(<span><span><span></span>listen</span></span>)</span>; names in other languages) is the capital and largest city of Serbia. It is located at the confluence of the Sava and Danube rivers and the crossroads of the Pannonian Plain and the Balkan Peninsula. Nearly 1.7\u00a0million people live within the administrative limits of the City of Belgrade, a quarter of the total population of Serbia.</p><p>Belgrade is one of the oldest continuously inhabited cities in Europe and the World. One of the most important prehistoric cultures of Europe, the Vin\u010da culture, evolved within the Belgrade area in the 6th millennium BC. In antiquity, Thraco-Dacians inhabited the region and, after 279 BC, Celts settled the city, naming it <i>Singid\u016bn</i>. It was conquered by the Romans under the reign of Augustus and awarded Roman city rights in the mid-2nd century. It was settled by the Slavs in the 520s, and changed hands several times between the Byzantine Empire, the Frankish Empire, the Bulgarian Empire, and the Kingdom of Hungary before it became the seat of the Serbian king Stefan Dragutin in 1284. Belgrade served as capital of the Serbian Despotate during the reign of Stefan Lazarevi\u0107, and then his successor \u0110ura\u0111 Brankovi\u0107 returned it to the Hungarian king in 1427. Noon bells in support of the Hungarian army against the Ottoman Empire during the siege in 1456 have remained a widespread church tradition to this day. In 1521, Belgrade was conquered by the Ottomans and became the seat of the Sanjak of Smederevo. It frequently passed from Ottoman to Habsburg rule, which saw the destruction of most of the city during the Austro-Ottoman wars. \n</p><p>In the period after the Serbian Revolution, Belgrade was again named the capital of Serbia in 1841. Northern Belgrade remained the southernmost Habsburg post until 1918, when it was attached to the city, due to former Austro-Hungarian territories becoming the part of the new Kingdom of Serbs, Croats and Slovenes after World War I. Belgrade was the capital of Yugoslavia from its creation in 1918 to its dissolution in 2006. In a fatally strategic position, the city has been battled over in 115 wars and razed 44 times, being bombed five times and besieged many times.</p><p>Being Serbia's primate city, Belgrade has special administrative status within Serbia. It is the seat of the central government, administrative bodies, and government ministries, as well as home of almost all of the largest Serbian companies, media, and scientific institutions. Belgrade is classified as a Beta-Global City. The city is home to the Clinical Centre of Serbia, one of the hospital complexes with the largest capacity in the world, the Church of Saint Sava, one of the largest Orthodox church buildings, and the \u0160tark Arena, one of the indoor arenas with the largest capacity in Europe. Belgrade hosted major international events such as the Danube River Conference of 1948, the first Non-Aligned Movement Summit (1961), the first major gathering of the OSCE (1977\u20131978), Eurovision Song Contest (2008), as well as sports events such as the first FINA World Aquatics Championships (1973), UEFA Euro (1976), Summer Universiade (2009) and EuroBasket three times (1961, 1975, 2005).\n</p>\n\n\n"
         }
      }
   }
}
```

- Fetching main image from article:  
https://en.wikipedia.org/w/api.php?action=query&titles=Belgrade&prop=pageimages&format=json&pithumbsize=250  
```json
{
   "batchcomplete":"",
   "query":{
      "pages":{
         "55904":{
            "pageid":55904,
            "ns":0,
            "title":"Belgrade",
            "thumbnail":{
               "source":"https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Beograd_collage.jpg/125px-Beograd_collage.jpg",
               "width":125,
               "height":250
            },
            "pageimage":"Beograd_collage.jpg"
         }
      }
   }
}
```

