package org.firespoon.fsbotclient.model

enum class Tag(val str: String) {
    Novice("新手"),
    Senior("资深干员"),
    SuperSenior("高级资深干员"),
    CloseRange("近战位"),
    LongRange("远程位"),
    Vanguard("先锋干员"),
    Sniper("狙击干员"),
    Healer("医疗干员"),
    Magician("术士干员"),
    Guard("近卫干员"),
    Heavy("重装干员"),
    Auxiliary("辅助干员"),
    SpecialType("特种干员"),
    Heal("治疗"),
    Support("支援"),
    Damage("输出"),
    GroupAttack("群攻"),
    SlowDown("减速"),
    Survival("生存"),
    Protect("防护"),
    Weaken("削弱"),
    Displacement("位移"),
    Control("控场"),
    Burst("爆发"),
    Summon("召唤"),
    QuicklyRebirth("快速复活"),
    CostRecover("费用回复"),
    SupportMachine("支援机械");

    override fun toString(): String {
        return str
    }
}
