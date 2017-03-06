如何使用ButterKnife？

1. 导入依赖项

    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath 'com.jakewharton:butterknife-gradle-plugin:8.5.1'//添加这里
        }
    }

    dependencies {
        compile fileTree(include: ['*.jar'], dir: 'libs')
        androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
            exclude group: 'com.android.support', module: 'support-annotations'
        })
        compile 'com.android.support:appcompat-v7:25.1.0'
        testCompile 'junit:junit:4.12'
        compile 'com.jakewharton:butterknife:8.5.1'//添加这里
        annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'//添加这里

    }
2. 绑定单个控件

    class ExampleActivity extends Activity {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.subtitle) TextView subtitle;
        @BindView(R.id.footer) TextView footer;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.simple_activity);
            ButterKnife.bind(this);
        }
    }
3. 绑定多个控件

    @BindViews({ R.id.first_name, R.id.middle_name, R.id.last_name })
    List<EditText> nameViews;
4. 资源绑定：
通过预定义的注解（@BindBool, @BindColor, @BindDimen, @BindDrawable, @BindInt, @BindString）结合一个资源 ID以和表示该资源类型的相应的字段。

    class ExampleActivity extends Activity {
        @BindString(R.string.title) String title;
        @BindDrawable(R.drawable.graphic) Drawable graphic;
        @BindColor(R.color.red) int red;
        @BindDimen(R.dimen.spacer) Float spacer;
        // ...
    }
5. Fragment中的绑定：

    public class FancyFragment extends Fragment {
        @BindView(R.id.button1) Button button1;
        @BindView(R.id.button2) Button button2;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fancy_fragment, container, false);
            ButterKnife.bind(this, view);
            return view;
        }
    }
6. List适配器中的用法：

    public class MyAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = inflater.inflate(R.layout.whatever, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }

            holder.name.setText("测试");

            return view;
        }

        static class ViewHolder {
            @BindView(R.id.title) TextView name;
            @BindView(R.id.job_title) TextView jobTitle;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
7. apply的用法：

    ButterKnife.apply(nameViews, DISABLE);
    ButterKnife.apply(nameViews, ENABLED, false);
8. Action 和 Setter 接口可以指定简单的行为：

    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };
9. 单个监听器绑定：

    @OnClick(R.id.submit)
    public void submit(View view) {

    }

    @OnClick(R.id.submit)
    public void submit() {

    }
    //定义的指定类型将会被自动转换

    @OnClick(R.id.submit)
    public void sayHi(Button button) {
        button.setText("Hello!");
    }
10. 多个监听器绑定：

    @OnClick({ R.id.door1, R.id.door2, R.id.door3 })
    public void pickDoor(DoorView door) {
        if (door.hasPrizeBehind()) {
            Toast.makeText(this, "You win!", LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Try again", LENGTH_SHORT).show();
        }
    }
11. 重置绑定：
Fragment的生命周期与Activity不同，当我们在onCreateView绑定Fragment，在onDestroyView将View 设置为null时，Butter Knife 的 unbind()方法可以帮我们做这些。

    public class FancyFragment extends Fragment {
        @Bind(R.id.button1) Button button1;
        @Bind(R.id.button2) Button button2;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fancy_fragment, container, false);
            ButterKnife.bind(this, view);
            return view;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
    }
12. 可选绑定：
默认情况下，”@Bind”和”@OnClick”(或者其他监听)监听绑定都是必需的。如果不能找到目标视图，则会引发异常。
为了制止这种行为，创建一个可选的结合，添加一个‘@Nullable’注解字段或方法。
任何名字为@Nullable的注解可以被这样使用。鼓励你使用Android自己的注解库”support-annotations”中的@Nullable注解，参见Android Tools Project.

    @Nullable
    @Bind(R.id.might_not_be_there) TextView mightNotBeThere;

    @Nullable
    @OnClick(R.id.maybe_missing) void onMaybeMissingClicked() {

    }
13. 多元监听：
与方法注解相匹配的监听器有多个回调可以被用来绑定在他们中间的任何一个身上。每一个注解都有默认的回调跟它绑定在一起。可以使用callback参数声明一个可替代的回调。

    @OnItemSelected(R.id.list_view)
    void onItemSelected(int position) {

    }

    @OnItemSelected(value = R.id.maybe_missing, callback = NOTHING_SELECTED)
    void onNothingSelected() {

    }