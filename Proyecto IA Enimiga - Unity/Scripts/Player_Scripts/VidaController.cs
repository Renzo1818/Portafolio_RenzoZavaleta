using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class VidaController : MonoBehaviour
{
    private Slider slider;
    private Animator animator;

    private void Start()
    {
        slider = GetComponent<Slider>();
        animator = GetComponent<Animator>();
    }

    public void ChangeMaxHealth(float maxHealth)
    {
        slider.maxValue = maxHealth;
    }

    public void ChangeCurrentHealth(float healthAmount)
    { 
        slider.value = healthAmount;
        animator.SetTrigger("Golpe");
    }

    public void CreateHealthBar(float healhAmount)
    {
        ChangeMaxHealth(healhAmount);
        ChangeCurrentHealth(healhAmount);
    }

}
