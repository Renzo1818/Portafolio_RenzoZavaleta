using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScoreController : MonoBehaviour
{
  // Start is called before the first frame update
  public int score;
    void Awake()
    {
        score = 0;   
    }
  public void OnTriggerEnter2D(Collider2D collision)
  {
    if (collision.gameObject.tag == "llave")
    {
      score++;
      Debug.Log(score);
    }
  }

  public int valor()
  {
    return score;
  }
}
