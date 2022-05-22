package com.example.mygame.OnlineMode.Classes;

import com.example.mygame.Act;
import com.example.mygame.Answer;
import com.example.mygame.Effect;
import com.example.mygame.R;

import java.io.Serializable;

public class StorageOnline implements Serializable {
    CountryOnline[] countries = {
            new CountryOnline("Гоуленд", 0, R.drawable.goland),
            new CountryOnline("Догсленд", 1, R.drawable.dogsland),
            new CountryOnline("Грандерберг", 2, R.drawable.granderberg),
            new CountryOnline("Золотая страна", 3, R.drawable.goldland),
            new CountryOnline("Камения", 4, R.drawable.stoneland),
            new CountryOnline("Эйрленд", 5, R.drawable.airland),
            new CountryOnline("Алмазная страна", 6, R.drawable.diamondland),
            new CountryOnline("Гринленд", 7, R.drawable.greenland)
    };

    public Act[] acts = new Act[]{
            new Act("Наши агенты узнали, что у нас есть угроза нападения на востоке. Что будем делать?", new Answer[]{
                    new Answer("Укрепим крепость", new Effect(0, 0, -0.17, 0.17, 0)),
                    new Answer("Успокоимся...", new Effect(0, 0, 0.17, -0.17, 0))
            }),
            new Act("На южной границе горит небольшое село! Что будем делать?", new Answer[]{
                    new Answer("Если маленькое, то ничего", new Effect(0, -0.17,0.17,0, 0)),
                    new Answer("Срочно тушить", new Effect(0, 0.17, -0.17, 0, 0))
            }),
            new Act("100 рабочих подписало петицию о сокращении рабочего дня!", new Answer[]{
                    new Answer("Сократим рабочий день на час", new Effect(-0.17,0.17,0,0, 0)),
                    new Answer("Скоратим кол-во этих рабочих", new Effect(0, -0.17, 0.17, 0, 0))
            }),
            new Act("Народ с восточной границы жалуется на нападения чужеземцев!", new Answer[]{
                    new Answer("Это их проблемы", new Effect(0, -0.17, 0, 0, -0.17)),
                    new Answer("Отправить туда военных", new Effect(0, 0.17, 0, -0.17, 0.17))
            }),
            new Act("Представитель предпринимателей хочет добиться налоговых льгот для большого бизнеса", new Answer[]{
                    new Answer("Ещё чего!", new Effect(-0.17, 0, 0.17, 0, 0)),
                    new Answer("Так уж и быть!", new Effect(0.17, 0, -0.17, -0.17, 0))
            }),
            new Act("Министр иностранных дел предложил отменить таможенную пошлину для союза Золотых стран", new Answer[]{
                    new Answer("Это может быть опасно!", new Effect(-0.17, 0, -0.17, 0.17, 0)),
                    new Answer("Хорошая идея!", new Effect(0.17, 0, 0.17, -0.17, 0))
            }),
            new Act("Люди из дальнего региона просят вас построить новую больницу и школы", new Answer[]{
                    new Answer("Я рассмотрю этот вопрос", new Effect(0, -0.17, +0.17, 0, -0.17)),
                    new Answer("ОК", new Effect(0, +0.17, -0.17, 0, +0.17))
            }),
            new Act("Народ с южной границы жалуется на военный беспредел", new Answer[]{
                    new Answer("Отправить ревизора", new Effect(0.17, 0.17, 0, -0.17, 0)),
                    new Answer("Пускай веселятся", new Effect(-0.17, -0.17, 0, +0.17, 0))
            }),
            new Act("Предприниматели требуют освободить от некоторых законов", new Answer[]{
                    new Answer("Я за равенство", new Effect(-0.17, +0.17, 0, 0, 0)),
                    new Answer("Хорошо, никто не пострадает", new Effect(+0.17, -0.17, 0, 0, 0))
            }),
            new Act("Люди всё больше и больше выбирают нездоровую пищу, что мы будем делать?", new Answer[]{
                    new Answer("Пусть едят, что хотят", new Effect(0, 0, +0.17, 0, -0.17)),
                    new Answer("Увеличим кол-во ECO-ферм", new Effect(0, 0, -0.17, 0, +0.17))
            }),
            new Act("Учёные просят предоставить им немного средств на изобретение новых технологий", new Answer[]{
                    new Answer("Денег нет", new Effect(-0.17, -0.17, 0.17, 0, 0)),
                    new Answer("Пусть берут", new Effect(0.17, 0.17, -0.17, 0, 0))
            }),
            new Act("Есть предолжение продать немного еды за границу", new Answer[]{
                    new Answer("Было бы не плохо", new Effect(0,0,+0.17, 0, -0.17)),
                    new Answer("У самих еды нет", new Effect(0, 0, -0.17, 0, +0.17))
            }),
            new Act("В одной из заграничных стран проблемы с едой, люди голодают", new Answer[]{
                    new Answer("Мы тоже не богатеи", new Effect(0, 0, 0, 0, +0.17)),
                    new Answer("Отправить гум. помощь", new Effect(0, 0, 0, 0, -0.17)),
            }),
            new Act("В одном из городов вырос уровень преступности, что будем делать?", new Answer[]{
                    new Answer("Отправим военных", new Effect(0, 0, 0, -0.17, 0)),
                    new Answer("Усилим полицию", new Effect(0, 0, -0.17, 0, 0))
            }),
            new Act("У дворца собрались люди и просят дать права домашним животным", new Answer[]{
                    new Answer("Разогнать дурачков", new Effect(0, -0.17, 0, 0, 0)),
                    new Answer("Собрать собрание", new Effect(0, +0.17, -0.17, 0, 0))
            }),
            new Act("Хочешь проиграть?", new Answer[]{
                    new Answer("Да", new Effect(-3, -3, -3, -3, -3)),
                    new Answer("Не очень", new Effect(0, 0, 0, 0, 0))
            }),
            new Act("Учёные на пороге нового открытия в сфере военного вооружения! Им необходимо ещё немного средств.", new Answer[]{
                    new Answer("Дать", new Effect(0, 0, -0.17, 0.17, 0)),
                    new Answer("Не дать", new Effect(0, 0, +0.17, -0.17, 0))
            }),
            new Act("Ваша партия предлагает дать больше прав трудящимся!", new Answer[]{
                    new Answer("Куда уж больше?", new Effect(0, -0.17, 0, 0, 0)),
                    new Answer("Согласен", new Effect(0, 0.17, 0, 0, 0))
            }),
            new Act("В посёлке, находящимся в 250 км от столицы, предлагают построить большой краеведческий музей. Вы согласны?", new Answer[]{
                    new Answer("Мы согласны", new Effect(0, 0.17, -0.17, 0, 0)),
                    new Answer("Не жирно ли будет", new Effect(0, -0.17, +0.17, 0, 0))
            }),
            new Act("По стране прошла новость о недавном суде. Вас просят помочь разобраться. Женщина украла ребёнка из неблагополучной семьи и воспитывает как родного.", new Answer[]{
                    new Answer("Посадить её в тюрьму", new Effect(0, -0.17, 0, 0, 0)),
                    new Answer("Оправдать и оставить ребёнка", new Effect(0, +0.17, 0, 0, 0))
            }),
            new Act("У нас есть сведения, что в столице поселилась вооружённая группировка! Отправим военных на помощь?", new Answer[]{
                    new Answer("Да", new Effect(0, 0, -0.1, -0.2, 0)),
                    new Answer("Нет", new Effect(0, 0, +0.1, 0.2, 0))
            }),
            new Act("Есть предложение сделать 7-дневный рабочий день для продвижения экономики!", new Answer[]{
                    new Answer("Отличная мысль", new Effect(-0.17, -0.17, +0.17, 0, 0)),
                    new Answer("Это перебор", new Effect(0, 0, 0, 0, 0))
            }),
            new Act("Может быть стоит обновить трактора в сёлах? Закупим их у других стран?", new Answer[]{
                    new Answer("Да нее", new Effect(0, -0.17, 0, 0, -0.17)),
                    new Answer("Это нам поможет", new Effect(0, +0.17, 0, 0, +0.17))
            }),
            new Act("По предположению синоптиков, вот-вот начнётся сильный зной. Может раздавать на улицах воду?", new Answer[]{
                    new Answer("Это должно помочь", new Effect(0, +0.17, -0.17, 0, 0)),
                    new Answer("Нее, дорого", new Effect(0, -0.17, +0.17, 0, 0))
            }),
            new Act("Представитель предпринимателей хочет добиться новых льгот для союза!", new Answer[]{
                    new Answer("Хорошо", new Effect(0.17, 0, -0.17, 0, 0)),
                    new Answer("Нет", new Effect(-0.17, 0, +0.17, 0, 0))
            }),
            new Act("Вы не хотите увеличить срок обязательной службы в армии до 3 лет?", new Answer[]{
                    new Answer("Хммм, да", new Effect(0, 0, 0, +0.17, 0)),
                    new Answer("Нет, это слишком", new Effect(0, 0, 0, -0.17, 0))
            }),
            new Act("Есть вариант перестроить небольшой жилой город под большие производства", new Answer[]{
                    new Answer("Надо", new Effect(+0.17, +0.17, -0.17, 0, -0.17)),
                    new Answer("Не надо", new Effect(-0.17, -0.17, +0.17, 0, 0.17))
            }),
            new Act("Вы не хотите сделать больше бюджетных мест в ВУЗах?", new Answer[]{
                    new Answer("Хочу", new Effect(0, 0, -0.17, 0, 0)),
                    new Answer("Нет", new Effect(0, 0, +0.17, 0, 0))
            }),
            new Act("Среди политиков возникла идея сделать повышенной налог для богатых! Как вы к этому относитесь?", new Answer[]{
                    new Answer("Поддерживаю", new Effect(-0.17, 0, +0.17, 0, 0)),
                    new Answer("Это не честно", new Effect(+0.17, 0, -0.17, 0, 0))
            }),
            new Act("Было бы не плохо сделать субботник обязательным!", new Answer[]{
                    new Answer("Нет, плохо", new Effect(0, -0.17, +0.17, 0, 0)),
                    new Answer("Согласен", new Effect(0, +0.17, -0.17, 0 ,0))
            }),
            new Act("Вы не хотите начать продавать нашу новейшую вооружённую технику?", new Answer[]{
                    new Answer("Нет, это опасно для нас", new Effect(0, 0, 0, +0.17, 0)),
                    new Answer("Хочу, деньги нам нужны", new Effect(0, 0, +0.17, -0.17, 0))
            }),
            new Act("В нашей стране всё больше и больше нелегально проживающих мигрантов! Надо что-то предпринять", new Answer[]{
                    new Answer("Усилить полицию", new Effect(0, 0, -0.17, 0, 0)),
                    new Answer("Оставить всё как есть", new Effect(0, 0, 0, 0, 0))
            }),
            new Act("Люди жалуются на высокие цены на ежедневные продукты!", new Answer[]{
                    new Answer("Контролировать эти цены", new Effect(-0.17, +0.17, 0, 0, 0)),
                    new Answer("Пусть больше зарабатывают", new Effect(+0.17, -0.17, 0, 0, 0))
            }),
            new Act("В современном мире необходимо больше вкладываться в развитие новых технологий!", new Answer[]{
                    new Answer("Не согласен", new Effect(0, 0, +0.17, 0, 0)),
                    new Answer("Согласен", new Effect(0, 0, -0.17, 0 ,0))
            }),
            new Act("Люди устроили протест на главной площади. Они требовали повышения зарплат! Как мы их накажем?", new Answer[]{
                    new Answer("Простим", new Effect(0, +0.17, 0, 0, 0)),
                    new Answer("Отправить на принудительные работы", new Effect(0, -0.17, +0.17, 0, +0.17))
            }),
            new Act("Вам предложили провести международную конференцию в вашей стране! На это нужно много денег, но зато мы получим известность!", new Answer[]{
                    new Answer("Никаких конференций", new Effect(0, -0.17, +0.17, 0, 0)),
                    new Answer("Провести конференцию", new Effect(0, +0.17, -0.17, 0, 0))
            }),
            new Act("Я считаю, что было бы неплохо забирать часть урожая у деревенских жителей!", new Answer[]{
                    new Answer("А я так не считаю", new Effect(0, +0.17, 0, 0, -0.17)),
                    new Answer("Я тоже так считаю", new Effect(0, -0.17, 0, 0, +0.17))
            }),
            new Act("Люди всё меньше и меньше доверяют правительству! Я думаю во всём виновато телевидение! Усилить цензуру и контроль?", new Answer[]{
                    new Answer("Да", new Effect(-0.17, 0, 0, 0, 0)),
                    new Answer("Нет", new Effect(+0.17, 0, -0.17, 0, 0))
            })

            //            new Act("", new Answer[]{
//                    new Answer("", new Effect()),
//                    new Answer("", new Effect())
//            }),
    };

    public int[] imagesAvatar = {
            R.drawable.pink_flag, R.drawable.green_flag, R.drawable.sword, R.drawable.earth
    };

    public int getResImageAvatar(int index){
        return imagesAvatar[index];
    }

    public Act getRandomAct(){
        int value = (int)(Math.random() * acts.length);
        while(acts[value] == null){
            value = (int)(Math.random() * acts.length);
        }
        Act act = acts[value];
        acts[value] = null;
        return act;
    }
}
