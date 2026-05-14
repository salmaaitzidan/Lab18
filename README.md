# ViewModelLiveDataDemo 

Application Android démontrant l'utilisation de **ViewModel** et **LiveData** avec le pattern MVVM.
Lab18.docx document word contyient touts les tests avec les screenshots
---

## Fonctionnalités

- Compteur avec incrément, décrément et réinitialisation
- Survie du compteur à la rotation d'écran
- Survie au changement de thème sombre/clair
- Mise à jour automatique de l'UI via LiveData
- Persistance après kill process (avec SavedStateHandle)



##  Concepts illustrés

### ViewModel
- Survit à la destruction/recréation de l'Activity (rotation, changement de thème)
- Stocké dans le `ViewModelStore` de l'Activity
- Détruit uniquement quand l'Activity finit définitivement

### LiveData
- Lifecycle-aware : notifie l'UI seulement si l'Activity est en `STARTED` ou `RESUMED`
- Pas de memory leak : l'observer est retiré automatiquement à `onDestroy()`
- `MutableLiveData` (interne, writable) vs `LiveData` (exposé, read-only)


### SavedStateHandle
- Combine les avantages de ViewModel et de `onSaveInstanceState`
- Survit au kill process (app en arrière-plan tuée par le système)

---

## Tableau de survie

| Événement | ViewModel seul | ViewModel + SavedStateHandle |
|---|---|---|
| Rotation d'écran | ok | ok |
| Thème sombre/clair | non | ok |
| App en arrière-plan (RAM dispo) | ok | ok |
| `adb shell am kill` | non | ok |

---

## Tests effectués

### Test 1 — Rotation
```
Incrémenter jusqu'à 10 → tourner l'écran → compteur intact ✅
```

### Test 2 — Thème sombre
```
adb shell cmd uimode night yes   # activer thème sombre
adb shell cmd uimode night no    # revenir thème clair
# compteur intact 
```

### Test 3 — Kill process
```bash
# 1. Incrémenter jusqu'à 15
# 2. Appuyer sur Home
adb shell am kill com.example.viewmodellivedatademoenrichi
# 3. Relancer l'app
# Sans SavedStateHandle → compteur = 0 
# Avec SavedStateHandle → compteur = 15
```

### Test 4 — Sans LiveData
```
Commenter le bloc observe() → cliquer INCRÉMENTER
→ UI ne se met pas à jour (valeur change dans le ViewModel mais pas à l'écran)
```
